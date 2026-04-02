# Lapis Client

A powerful PVP-focused Minecraft client mod for Fabric 1.21.1

## Features

### HUD Modules
- **Armor HUD**: Display armor durability with customizable percentage and color coding
  - Toggle percentage display
  - Toggle durability numbers
  - Color-coded based on remaining durability (green > yellow > red)
  - Customizable background and text colors

- **FPS Indicator**: Real-time FPS counter with auto-adjusting width

- **Ping Indicator**: Display your current ping with color-coded performance
  - Green: < 50ms
  - Orange: 50-100ms
  - Red: > 100ms

- **Potion Indicator**: Shows active potion effects with remaining duration

- **Coordinates**: Display your current XYZ position

- **Direction**: Show which cardinal direction you're facing

### Performance Modules
- **Low Fire**: Removes the fire overlay when burning for better visibility
- **Low Shield**: Reduces shield overlay height
- **Particle Reducer**: Reduces particle effects for better FPS
- **Entity Culling**: Optimizes rendering by not drawing invisible entities
- **Smart Chunk Updates**: Optimizes chunk update rendering

### Render Modules
- **Zoom**: Press and hold **C** to zoom in (similar to OptiFine zoom)
- **Fullbright**: Maximum brightness at all times

### Other Features
- **YouTube Notifications**: Automatically notifies you when NaiteroOfficial uploads a new video
  - In-game popup with clickable link
  - Sound notification
  - Checks every 5 minutes

- **Lapis Icon**: Shows a lapis lazuli icon next to your nametag (client indicator)

- **Draggable HUD Elements**: All HUD modules can be repositioned (feature foundation implemented)

- **Config System**:
  - Automatic saving when closing game or disconnecting
  - JSON-based configuration storage
  - Per-module settings preservation

- **Configuration GUI**:
  - Press **9** to open
  - Browse modules by category
  - Toggle modules with left-click
  - Open settings with right-click

## Installation

### Requirements
- Minecraft 1.21.1
- Fabric Loader 0.16.5 or higher
- Fabric API 0.105.0 or higher
- Java 21 or higher

### Steps
1. Install Fabric Loader from https://fabricmc.net/use/
2. Download Fabric API from https://modrinth.com/mod/fabric-api
3. Build this mod (see "Building from Source" below)
4. Place `lapis-client-1.0.0.jar` in your `.minecraft/mods` folder
5. Launch Minecraft with the Fabric profile

## Usage

### Keybinds
- **9**: Open Lapis Client configuration GUI
- **C**: Zoom (hold to zoom)

### GUI Controls
- **Left-click** on module: Toggle on/off
- **Right-click** on module: Open settings panel
- **Left-click** category header: Expand/collapse category
- Settings are saved automatically when you close the GUI or exit the game

## Building from Source

### Prerequisites
- Java Development Kit (JDK) 21 or higher
- Internet connection (for downloading dependencies)

### Build Commands

**Windows:**
```cmd
gradlew.bat build
```

**macOS/Linux:**
```bash
chmod +x gradlew
./gradlew build
```

The compiled JAR will be located in `build/libs/lapis-client-1.0.0.jar`

For detailed build instructions and troubleshooting, see [BUILD_INSTRUCTIONS.md](BUILD_INSTRUCTIONS.md)

## Project Structure

```
lapis-client/
├── src/main/java/com/lapisclient/
│   ├── LapisClient.java          # Main mod entry point
│   ├── config/
│   │   └── ConfigManager.java    # Configuration system
│   ├── module/
│   │   ├── Module.java           # Base module class
│   │   ├── ModuleCategory.java   # Module categories
│   │   ├── ModuleManager.java    # Module registry
│   │   └── modules/
│   │       ├── hud/              # HUD modules
│   │       ├── performance/      # Performance modules
│   │       └── render/           # Render modules
│   ├── ui/
│   │   └── ClickGuiScreen.java   # Configuration GUI
│   ├── youtube/
│   │   └── YouTubeNotifier.java  # YouTube notification system
│   └── mixin/                    # Minecraft mixins
└── src/main/resources/
    ├── fabric.mod.json           # Mod metadata
    └── lapisclient.mixins.json   # Mixin configuration
```

## Configuration

Configuration is stored in `.minecraft/config/lapisclient/config.json`

Settings include:
- Module enabled states
- Module positions (for HUD elements)
- Visual customization (colors, backgrounds, opacity)
- Module-specific settings (e.g., Armor HUD percentage display)

## Known Limitations

1. **HUD Dragging**: The dragging system foundation is implemented but requires further enhancement for full drag-and-drop functionality
2. **Icon Display**: The lapis icon next to nametags requires actual PNG icon assets to be created
3. **Performance Modules**: Some performance modules are placeholders and need additional implementation for full optimization effects

## Future Enhancements

- Enhanced drag-and-drop for HUD repositioning
- More PVP-focused modules (reach display, CPS counter, etc.)
- Custom module creation system
- Profile system for different configurations
- Multiplayer mod detection and synchronization

## License

MIT License - See [LICENSE](LICENSE) file for details

## Credits

Created for the Minecraft PVP community
Inspired by Feather Client and other popular Minecraft clients
