# Limbo Auth
Limbo Auth is the best (and only) Authorization plugin for Limbo (https://github.com/LOOHP/Limbo/).

## Why use Limbo Auth
### Secure
Limbo Auth is built with security in mind, everything is encrypted BEFORE it gets sent to the database, so even with a MySQL database breach, none of your users login passwords wil be compromised!
### Lightweight and Fast
Limbo Auth is lightweight and fast! Encryption and Dencryption of the passwords happens in milliseconds, as does API triggers!
## Customizable
Almost every aspect of Limbo Auth is customizable, down to the messages sent to the players, to the commands ran on correct authorization! And if the config isnt enough to satisfy your needs, there is a simple yet robust API that you can use to create your own plugin addons!

## Setup
1. Stop Limbo server
2. Install latest version of Limbo Auth into plugins folder
3. **OPTIONAL** Configure MySQL (SQLite will be used if not!) in config.yml
4. **OPTIONAL** Configure any messages you'd like too in messages.yml
5. **OPTIONAL** Configure the commands that are run when a player is authorized in config.yml
6. Start the server and enjoy Limbo Auth!

## API Documentation
PlayerAuthEvent - Fires whenever a player logs in !
```
/** Returns the player
	 * 
	 * @return The player variable
	 */
public Player getPlayer() {
  return player;
}
/** Returns whether or not the player used the right password
	 * 
	 * @return Whether or not the player used the right password
	 */
public Boolean isAuthorized() {
  return isAuth;
}
```

## Known Issues:
### Console Being Spammed When Player Joins
If you are getting this error or similar:
```
[18:03:51 Error] Error while passing com.loohp.limbo.events.player.PlayerMoveEvent to the plugin "LimboAuth"
[18:03:51 Error] java.lang.reflect.InvocationTargetException
[18:03:51 Error] 	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
[18:03:51 Error] 	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
[18:03:51 Error] 	at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
[18:03:51 Error] 	at java.base/java.lang.reflect.Method.invoke(Method.java:568)
[18:03:51 Error] 	at com.loohp.limbo.events.EventsManager.callEvent(EventsManager.java:27)
[18:03:51 Error] 	at com.loohp.limbo.network.ClientConnection.run(ClientConnection.java:519)
[18:03:51 Error] Caused by: java.lang.NullPointerException: Cannot invoke "java.lang.Integer.intValue()" because the return value of "java.util.Map.get(Object)" is null
[18:03:51 Error] 	at com.loohp.limbo.network.protocol.packets.ClientboundSetSubtitleTextPacket.serializePacket(ClientboundSetSubtitleTextPacket.java:30)
[18:03:51 Error] 	at com.loohp.limbo.network.ClientConnection.sendPacket(ClientConnection.java:178)
[18:03:51 Error] 	at com.loohp.limbo.player.Player.sendTitlePart(Player.java:447)
[18:03:51 Error] 	at com.loohp.limbo.player.Player.setTitleSubTitle(Player.java:380)
[18:03:51 Error] 	at com.gamerduck.listeners.PlayerMoveListener.onMove(PlayerMoveListener.java:15)
[18:03:51 Error] 	... 6 more
```
Please update your mappings.json in internal_data to this:
```
{
  "HandshakeIn": {
    "0x00": "PacketHandshakingIn"
  },
  "LoginIn": {
    "0x00": "PacketLoginInLoginStart",
    "0x02": "PacketLoginInPluginMessaging"
  },
  "LoginOut": {
    "PacketLoginOutLoginSuccess": "0x02",
    "PacketLoginOutDisconnect": "0x00",
    "PacketLoginOutPluginMessaging": "0x04"
  },
  "PlayIn": {
    "0x0F": "PacketPlayInKeepAlive",
    "0x03": "PacketPlayInChat",
    "0x12": "PacketPlayInPositionAndLook",
    "0x11": "PacketPlayInPosition",
    "0x13": "PacketPlayInRotation",
    "0x0A": "PacketPlayInPluginMessaging",
    "0x06": "PacketPlayInTabComplete",
    "0x25": "PacketPlayInHeldItemChange",
    "0x21": "PacketPlayInResourcePackStatus"
  },
  "PlayOut": {
    "PacketPlayOutLogin": "0x26",
    "PacketPlayOutPositionAndLook": "0x38",
    "PacketPlayOutSpawnPosition": "0x4B",
    "PacketPlayOutChat": "0x0F",
    "PacketPlayOutPlayerAbilities": "0x32",
    "ClientboundLevelChunkWithLightPacket": "0x22",
    "PacketPlayOutUnloadChunk": "0x1D",
    "PacketPlayOutKeepAlive": "0x21",
    "PacketPlayOutPlayerInfo": "0x36",
    "PacketPlayOutUpdateViewPosition": "0x49",
    "PacketPlayOutDisconnect": "0x1A",
    "PacketPlayOutPluginMessaging": "0x18",
    "PacketPlayOutTabComplete": "0x11",
    "PacketPlayOutDeclareCommands": "0x12",
    "PacketPlayOutRespawn": "0x3D",
    "PacketPlayOutGameState": "0x1E",
    "PacketPlayOutEntityDestroy": "0x3A",
    "PacketPlayOutEntityMetadata": "0x4D",
    "PacketPlayOutSpawnEntity": "0x00",
    "PacketPlayOutSpawnEntityLiving": "0x02",
    "PacketPlayOutHeldItemChange": "0x48",
    "PacketPlayOutPlayerListHeaderFooter": "0x5F",
    "PacketPlayOutResourcePackSend": "0x3C",
    "ClientboundSetTitlesAnimationPacket": "0x5B", 
    "ClientboundSetTitleTextPacket": "0x5A",
    "ClientboundSetSubtitleTextPacket": "0x58",
    "ClientboundClearTitlesPacket": "0x10"
  },
  "StatusIn": {
    "0x01": "PacketStatusInPing",
    "0x00": "PacketStatusInRequest"
  },
  "StatusOut": {
    "PacketStatusOutResponse": "0x00",
    "PacketStatusOutPong": "0x01"
  }
}
```
### Any Issue Not Posted Here
If you are having any other issue please don't hesitate to contact me on discord @ GamerDuck123#9999
