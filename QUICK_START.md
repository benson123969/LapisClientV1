# Quick Start Guide

## For Users Who Just Want to Use the Mod

### Step 1: Build the Mod
You need Java 21 installed. Download from: https://adoptium.net/

**Windows:**
```cmd
gradlew.bat build
```

**Mac/Linux:**
```bash
./gradlew build
```

### Step 2: Install
1. Install Fabric Loader: https://fabricmc.net/use/
2. Install Fabric API: https://modrinth.com/mod/fabric-api
3. Copy `build/libs/lapis-client-1.0.0.jar` to `.minecraft/mods`
4. Launch Minecraft 1.21.1 with Fabric

### Step 3: Use It!
- Press **9** to open menu
- Press **C** to zoom
- Left-click to toggle modules
- Right-click for settings

## What You Get

### Always-On Features
- FPS counter (top left)
- Armor durability display (left side)
- Ping indicator
- YouTube notifications from NaiteroOfficial

### Modules You Can Enable
- Potion effects display
- Coordinates display
- Direction indicator
- Low fire overlay
- Low shield overlay
- Zoom (C key)
- Fullbright
- Various FPS boost modules

### Customization
Every HUD module lets you customize:
- Background color
- Text color
- Show/hide background
- Position on screen
- Module-specific options

## Keybinds

| Key | Action |
|-----|--------|
| 9 | Open config menu |
| C | Zoom (hold) |

## Tips

1. **First time setup**: Press 9 and explore the modules
2. **Enable what you need**: Not all modules are on by default
3. **Settings save automatically**: Close the game and your settings persist
4. **YouTube notifications**: You'll get notified when NaiteroOfficial uploads

## Troubleshooting

**Mod doesn't show up:**
- Make sure you have Fabric Loader AND Fabric API
- Check you're running Minecraft 1.21.1
- Look at the Minecraft logs for errors

**Build fails:**
- Make sure Java 21+ is installed
- Run `java -version` to check
- Make sure you have internet connection

**Config menu won't open:**
- Press 9 (not the numpad 9)
- Make sure you're in-game, not in another menu

## Configuration Location

Your settings are saved in:
```
.minecraft/config/lapisclient/config.json
```

You can:
- Back it up
- Share it with friends
- Edit it manually (advanced)

## Getting Help

1. Check BUILD_INSTRUCTIONS.md for detailed build help
2. Check PROJECT_SUMMARY.md for technical details
3. Read README.md for full feature list

## Credits

Built as a Fabric mod for Minecraft 1.21.1
Inspired by Feather Client
Created for the PVP community
