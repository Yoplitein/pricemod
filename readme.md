# pricemod
A Wurm Unlimited mod that lets you change the prices of items sold by traders.

## Building
Requires [Maven](https://maven.apache.org/) and, obviously, a JDK.

Copy `server.jar` (from `SteamApps/Common/WurmUnlimited/WurmServerLauncher/`, or your server root) to the `libs/` folder.
Run `mvn compile jar:jar assembly:single` to build the distribution zip. You can find it under the newly created `target/` directory.
