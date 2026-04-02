package com.lapisclient.youtube;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.lapisclient.LapisClient;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.sound.PositionedSoundInstance;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.ClickEvent;
import net.minecraft.text.Text;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class YouTubeNotifier {
	private static final String CHANNEL_ID = "UCxQRjBqKXd7wvC8jPGzPTqw"; // NaiteroOfficial channel ID
	private static final String RSS_URL = "https://www.youtube.com/feeds/videos.xml?channel_id=" + CHANNEL_ID;
	private static final long CHECK_INTERVAL = 300000; // 5 minutes in milliseconds

	private final OkHttpClient httpClient;
	private final Set<String> notifiedVideos;
	private Thread notifierThread;
	private boolean running;

	public YouTubeNotifier() {
		this.httpClient = new OkHttpClient.Builder()
			.connectTimeout(10, TimeUnit.SECONDS)
			.readTimeout(10, TimeUnit.SECONDS)
			.build();
		this.notifiedVideos = new HashSet<>();
	}

	public void start() {
		if (running) return;
		running = true;

		notifierThread = new Thread(() -> {
			while (running) {
				try {
					checkForNewVideos();
					Thread.sleep(CHECK_INTERVAL);
				} catch (InterruptedException e) {
					break;
				} catch (Exception e) {
					LapisClient.LOGGER.error("Error checking for new videos", e);
				}
			}
		});
		notifierThread.setDaemon(true);
		notifierThread.start();

		LapisClient.LOGGER.info("YouTube notifier started");
	}

	public void stop() {
		running = false;
		if (notifierThread != null) {
			notifierThread.interrupt();
		}
	}

	private void checkForNewVideos() {
		try {
			Request request = new Request.Builder()
				.url(RSS_URL)
				.build();

			Response response = httpClient.newCall(request).execute();
			if (!response.isSuccessful()) return;

			String body = response.body().string();
			parseRSSAndNotify(body);

		} catch (Exception e) {
			LapisClient.LOGGER.error("Failed to check for new videos", e);
		}
	}

	private void parseRSSAndNotify(String rss) {
		try {
			// Extract video ID from RSS feed (simple parsing)
			if (rss.contains("<yt:videoId>")) {
				String[] parts = rss.split("<yt:videoId>");
				if (parts.length > 1) {
					String videoId = parts[1].split("</yt:videoId>")[0];
					String videoUrl = "https://www.youtube.com/watch?v=" + videoId;

					if (!notifiedVideos.contains(videoId)) {
						notifiedVideos.add(videoId);
						showNotification(videoUrl);
					}
				}
			}
		} catch (Exception e) {
			LapisClient.LOGGER.error("Failed to parse RSS feed", e);
		}
	}

	private void showNotification(String videoUrl) {
		MinecraftClient client = MinecraftClient.getInstance();
		if (client.player == null) return;

		// Show chat message with clickable link
		Text message = Text.literal("§b§l[Lapis Client] §r§eNew video from NaiteroOfficial! ")
			.append(Text.literal("§a§n[Click to Watch]§r")
				.styled(style -> style.withClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, videoUrl))));

		client.player.sendMessage(message, false);

		// Play notification sound
		client.getSoundManager().play(PositionedSoundInstance.master(SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0f));

		LapisClient.LOGGER.info("New video notification sent: " + videoUrl);
	}
}
