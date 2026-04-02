# Building Lapis Client

## Prerequisites

To build this Minecraft Fabric mod, you need:

1. **Java Development Kit (JDK) 21 or higher**
   - Download from: https://adoptium.net/temurin/releases/
   - Make sure `java` is in your PATH
   - Verify installation: `java -version`

2. **Gradle** (included via wrapper, no separate installation needed)

3. **Internet connection** (for downloading dependencies)

## Build Steps

### On Windows:

```cmd
gradlew.bat build
```

### On macOS/Linux:

```bash
chmod +x gradlew
./gradlew build
```

## Output

After a successful build, you'll find the compiled mod JAR file in:

```
build/libs/lapis-client-1.0.0.jar
```

## Installation

1. Make sure you have **Minecraft 1.21.1** installed
2. Install **Fabric Loader** from https://fabricmc.net/use/
3. Download and install **Fabric API** from https://modrinth.com/mod/fabric-api
4. Copy `lapis-client-1.0.0.jar` to your `.minecraft/mods` folder
5. Launch Minecraft with the Fabric profile

## Troubleshooting

### "JAVA_HOME is not set"
- Install JDK 21 or higher
- Set JAVA_HOME environment variable to your JDK installation path

### Build fails with "Could not resolve dependencies"
- Check your internet connection
- Try running the build command again

### Mod doesn't load in Minecraft
- Verify you have Minecraft 1.21.1
- Verify you have Fabric Loader installed
- Verify you have Fabric API mod installed
- Check the Minecraft logs for errors

## Development

To set up a development environment:

1. Import the project into your IDE (IntelliJ IDEA recommended)
2. Run `./gradlew genSources` to generate Minecraft source code
3. Run `./gradlew runClient` to launch Minecraft with the mod for testing

## Features Overview

Once installed, you can:

- Press **9** to open the Lapis Client configuration GUI
- Press **C** to zoom in
- Toggle modules on/off by left-clicking them in the GUI
- Right-click modules to open their settings
- Drag HUD elements to reposition them (feature to be enhanced)

All settings are automatically saved to `.minecraft/config/lapisclient/config.json`
