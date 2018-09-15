# pricemod
A Wurm Unlimited mod that lets you change the prices of items sold by traders.

## Building
Requires [Maven](https://maven.apache.org/) and, obviously, a JDK.

Copy `server.jar` (from `SteamApps/Common/WurmUnlimited/WurmServerLauncher/`, or your server root) to the `libs/` folder.
Run `mvn compile jar:jar assembly:single` to build the distribution zip. You can find it under the newly created `target/` directory.

## Usage
After extracting the zip, create `mods/pricemod.config` in the following format:

```python
itemN=itemid,price
itemN=itemid2,price
...
```

* `N` can be any number as long as it is unique, otherwise only one of the specified items will be modified.
* `itemid` is the item template's ID, you can find a list [here](https://pastebin.com/teC3pYsD). You can acquire an up to date list yourself by decompiling `com.wurmonline.server.items.ItemList`. It may be necessary to cross reference these IDs with the `ItemTemplateCreator*` classes in that same items package.
* `price` is the new price of the item, in irons. You can find a table of coinage types and their value in irons at [the Wurm wiki](https://www.wurmpedia.com/index.php/Coin#Denominations).

A fully fledged example:

```python
price0=601,2500 #shaker orb to 25c
price1=299,100000 #trader contract to 10s
price2=300,50000 #merchant contract to 5s
price3=664,50000 #large magical chest to 5s
price4=665,10000 #small magical chest to 1s
price5=666,5000 #sleep powder to 50c
price6=668,250000 #transmutation rod to 25s
```
