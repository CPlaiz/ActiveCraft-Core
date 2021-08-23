---
layout: page
title: Commands
permalink: /commands/
---
# Commands

This is a overview of all the commands, their usage and associated permissions. 
The commands are split into categories:




## Message Commands

### /msg
Send a direct message to a online player.

*Usage*:
```
/msg <player> <message>
```

### /reply
Send a direct message to the last player who texted you.

*Aliases:* **/r**

*Usage*:
```
/r <message>
```

### /broadcast
Broadcasts a message to everyone on the server.

*Usage*:
```
/broadcast <message>
```

## Teleportation Commands

### /home
Teleports you to your home / a player's home.

*Usage*:
```
/home <player>
```
### /sethome
Sets your home / a player's home.

*Usage*:
```
/sethome <player>
```
### /delhome
Deletes your home / a player's home.

*Usage*:
```
/delhome <player>
```
### /warp
Teleports you to the given warp.

*Usage*:
```
/warp [warp_name]
```
### /setwarp
Creates a new warp.

*Usage*:
```
/setwarp [warp_name]
```
### /delwarp
Deletes a warp.

*Usage*:
```
/delwarp [warp_name]
```
### /warps
Lists all warps.

*Usage*:
```
/warps
```
### /tpa
Sends a teleport request to a player which can either be accepted(/tpaccept) or denied(tpdeny).

*Usage*:
```
/tpa [player]
```
### /tpaccept
Accepts the last tpa request.

*Usage*:
```
/tpaccept
```
### /tpdeny
Denies the last tpa request.

*Usage*:
```
/tpdeny
```
### /tphere
Teleports a player to your location.

*Usage*:
```
/tphere [player]
```
### /tpall
Teleports all players with out the permission *"activecraft.tpall.except"* to your location.

*Usage*:
```
/tpall
```
### /spawn
Teleports you to the spawn.

*Usage*:
```
/spawn
```
### /setspawn
Sets the spawn to your location.

*Usage*:
```
/setspawn
```
### /portal
<dl>
  <dt>Case</dt>
  <dd>create</dd>
  <dt>Usage</dt>
  <dd>/portal create [name] [x] [y] [z] [toX] [toY] [toZ]</dd>
  <dt>Description</dt>
  <dd>Creates a portal a the first given location which teleports to the second given location.</dd>
</dl>
<dl>
  <dt>Case</dt>
  <dd>destroy</dd>
  <dt>Usage</dt>
  <dd>/portal destroy [name]</dd>
  <dt>Description</dt>
  <dd>Destroys the portal with the given name..</dd>
</dl>
<dl>
  <dt>Case</dt>
  <dd>list</dd>
  <dt>Usage</dt>
  <dd>/portal list</dd>
  <dt>Description</dt>
  <dd>Lists all portals.</dd>
</dl>

## Player Management Commands

**Coming soon**
{: .label .label-yellow }