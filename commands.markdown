---
layout: page
title: Commands
permalink: /commands/
nav_order: 2
---

## Table of contents
{: .no_toc }
<details open markdown="block">
  <summary>
    Table of contents
  </summary>
  {: .text-delta }
1. TOC
{:toc}
</details>


# Commands
{: .no_toc }

This is an overview of all the commands, their usage and associated permissions. 

**IMPORTANT INFO**

Every section for a command has a *Usage* part. In this part **[arg]** stands for an argument necessary for the command. The angle brackets **\<arg>** are used to describe an optional argument. 

---

## Message Commands

### /msg
Send a direct message to an online player.

*Usage*:
```
/msg [player] [message]
```
*Permission*: 
```
activecraft.msg
```

---

### /reply
Send a direct message to the last player who texted you.

*Usage*:
```
/reply [message]
```
*Aliases:* 
```
/r
```
*Permission*: 
```
activecraft.reply
```


### /togglesocialspy
Sets whether you get SocialSpy messages or not.

*Usage*:
```
/togglesocialspy
```
*Permission*: 
```
activecraft.msg.spy
```

---

### /broadcast
Broadcasts a message to everyone on the server.

*Usage*:
```
/broadcast [message]
```
*Aliases:* 
```
/bc
```
*Permission*: 
```
activecraft.broadcast
```

---

### /broadcastworld
Broadcasts a message to everyone in your world.

*Usage*:
```
/broadcastworld [message]
```
*Aliases:*
```
/bcw
```
*Permission*: 
```
activecraft.broadcast
```

---

### /staffchat
Sends a message to all players with the specified permission.

*Usage*:
```
/staffchat [message]
```
*Aliases:* 
```
/sc
```
*Permission*: 
```
activecraft.staffchat
```

---

## Teleportation / Movement Commands

### /home
Teleports you to your home / a player's home.

*Usage*:
```
/home [home]
/home [player] [home]
```
*Permission*: 
```
activecraft.home.self
activecraft.home.others
```

---

### /sethome
Sets your home / a player's home.

*Usage*:
```
/sethome [home]
/sethome [player] [home]
```
*Permission*: 
```
activecraft.sethome.self
activecraft.sethome.others
activecraft.maxhomes.[amount]
```

---

### /delhome
Deletes your home / a player's home.

*Usage*:
```
/delhome [home]
/delhome [player] [home]
```
*Permission*: 
```
activecraft.delhome.self
activecraft.delhome.others
```

---

### /warp
Teleports you to the given warp.

*Usage*:
```
/warp [warp_name]
/warp [player] [warp_name]
```
*Permission*: 
```
activecraft.warp.self.[warp_name]
activecraft.warp.others.[warp_name]
```

---

### /setwarp
Creates a new warp.

*Usage*:
```
/setwarp [warp_name]
```
*Permission*: 
```
activecraft.warp.set
```

---

### /delwarp
Deletes a warp.

*Usage*:
```
/delwarp [warp_name]
```
*Permission*: 
```
activecraft.warp.delete
```

---

### /warps
Lists all warps.

*Usage*:
```
/warps
```
*Permission*: 
```
activecraft.warp.list
```

---

### /tpa
Sends a teleport request to a player which can either be accepted (**/tpaccept**) or denied (**/tpdeny**). 

*Usage*:
```
/tpa [player]
```
*Permission*: 
```
activecraft.tpa
```

---

### /tpaccept
Accepts the last tpa request.

*Usage*:
```
/tpaccept
```
*Permission*: 
```
activecraft.tpa
```

---

### /tpdeny
Denies the last tpa request.

*Usage*:
```
/tpdeny
```
*Permission*: 
```
activecraft.tpa
```

---

### /tphere
Teleports a player to your location.

*Usage*:
```
/tphere [player]
```
*Permission*: 
```
activecraft.tphere
```

---

### /tpall
Teleports all players with out the permission *"activecraft.tpall.except"* to your location.

*Usage*:
```
/tpall
```
*Permission*: 
```
activecraft.tpall
activecraft.tpall.except
```

---

### /tprandom
Teleports a player to a random location within the given range.

*Usage*:
```
/tpr <player> <range>
```
*Permission*: 
```
activecraft.randomtp.self
activecraft.randomtp.others
```

---

### /spawn
Teleports you to the spawn.

*Usage*:
```
/spawn <player>
```
*Permission*: 
```
activecraft.spawn.self
activecraft.spawn.others
```

---

### /setspawn
Sets the spawn to your location.

*Usage*:
```
/setspawn
```
*Permission*: 
```
activecraft.spawn.set
```

---

### /portal
create: Creates a portal a the first given location which teleports to the second given location. \
destroy: Destroys the portal with the given name. \
list: Lists all portals.

*Usage*:
```
/portal create [name] [x] [y] [z] [toX] [toY] [toZ]
/protal destroy [name]
/portal list
```

*Permission*:
```
activecraft.portal.create
activecraft.portal.destroy
activecraft.portal.list
```

---

### /fly
Toggles the flight mode for a player.

*Usage*:
```
/fly <player>
```
*Permission*:
```
activecraft.fly.self
activecraft.fly.others
```

---

### /flyspeed
Sets the flyspeed for a player.

*Usage*:
```
/flyspeed <player> [1-10]
```
*Permission*:
```
activecraft.flyspeed.self
activecraft.flyspeed.others
```

---

### /walkspeed
Sets the walkspeed for a player.

*Usage*:
```
/walkspeed <player> [1-5]
```
*Permission*:
```
activecraft.walkspeed.self
activecraft.walkspeed.others
```

---

### /back
Teleports to the location you died at or where you were before a teleport event.

*Usage*:
```
/back
```
*Permission*:
```
activecraft.back
```

---

## Player Management Commands
### /ban
Opens a ban dialogue for the given player.To cancel the dialogue type **cancel** or **quit** at any point in the process.

*Usage*:
1. Enter the command:
```
/ban [player]
```
2. Give the reason in chat
3. Enter a ban duration in the format **1m/1h/1d/1w/1M** (for permanent type **null** or **permanent**)
*Permission*: 
```
activecraft.ban
```

---

### /unban
Unban a banned player.

*Usage*:
```
/unban [player]
```
*Permission*:
```
activecraft.ban
```

---

### /ban-ip
Opens an ipban dialogue for the given player.To cancel the dialogue type **cancel** or **quit** at any point in the process.

*Usage*:
1. Enter the command:
```
/ban-ip [player]
```
2. Give the reason in chat
3. Enter a ban duration in the format **1m/1h/1d/1w/1M** (for permanent type **null** or **permanent**)
*Permission*:
```
activecraft.ban-ip
```

---

### /unban-ip
Unbans a banned player's IP address.

*Usage*:
```
/unban-ip [ip]
```
*Permission*:
```
activecraft.ban-ip
```

---

### /banlist
Opens the list of all banned and ipbanned players on the server.

*Usage*:
```
/banlist
```
*Permission*:
```
activecraft.banlist
```

---

### /kick
Kick a player from the server.

*Usage*:
```
/kick [player] <reason>
```
*Permission*:
```
activecraft.kick
```

---

### /kickall
Kicks all players without the permission *"activecraft.kickall.bypass"*.

*Usage*:
```
/kickall <reason>
```
*Permission*:
```
activecraft.kickall
activecraft.kickall.bypass
```

---

### /warn
Warns a player.

*Usage*:
```
/warn add [player] <reason>
/warn get [player] [reason]
/warn remove [player] [reason]
```
*Permission*:
```
activecraft.warn.add
activecraft.warn.get
activecraft.warn.remove
```

---

### /mute
Mutes a player.

*Usage*:
```
/mute [player]
```
*Permission*:
```
activecraft.mute
```

---

### /unmute
Unmutes a player.

*Usage*:
```
/unmute [player]
```
*Permission*:
```
activecraft.mute
```

---

### /forcemute
Mutes a player for admins. Even with the permission *"activecraft.muted.see"* they will not recieve a muted player's messages.

*Usage*:
```
/forcemute [player]
```
*Permission*:
```
activecraft.forcemute
```

---

### /forceunmute
Unmutes a player for admins. If a player has the permission *"activecraft.muted.see"*, they will still see muted player's messages.

*Usage*:
```
/forceunmute [player]
```
*Permission*:
```
activecraft.forcemute
```

---

### /clearinventory
Clears a player's inventory.

*Usage*:
```
/clearinventory [player]
```
*Aliases*:
```
/clear
```
*Permission*: 
```
activecraft.clearinv.self
activecraft.clearinv.others
```

---

### /colornick
Changes the base color of a player's displayname. (overwritten by colors used within */nick*)

*Usage*:
```
/colornick <player> [chat_color] 
```
*Permission*: 
```
activecraft.colornick.self
activecraft.colornick.others
```

---

### /afk
Adds a [AFK] tag to your player to signal that you are afk.

*Usage*:
```
/afk <player>
```
*Permission*: 
```
activecraft.afk.self
activecraft.afk.others
```

---

### /enderchest
Opens a player's enderchest.

*Usage*:
```
/enderchest <player>
```
*Aliases*:
```
/ec 
```
*Permission*:
```
activecraft.enderchest.self
activecraft.enderchest.others
```

---

### /heal
Heals and feeds a player.

*Usage*:
```
/heal <player>
```
*Permission*:
```
activecraft.heal.self
activecraft.heal.others
```

---

### /feed
Feeds a player.

*Usage*:
```
/feed <player>
```
*Permission*:
```
activecraft.feed.self
activecraft.feed.others
```

---

### /gamemode
Changes a player's gamemode.

*Usage*:
```
/gamemode <gamemode>
```
*Aliases*:
```
/su
/ad
/cr
/sp
```
*Permission*:
```
activecraft.gamemode.survival
activecraft.gamemode.adventure
activecraft.gamemode.creative
activecraft.gamemode.spectator
```

---

### /god
Makes a player invincible.

*Usage*:
```
/god <player>
```
*Permission*:
```
activecraft.god.self
activecraft.god.others
```

---

### /hat
Sets the held item the a player's helmet slot.

*Usage*:
```
/hat
```
*Permission*:
```
activecraft.hat
```

---

### /invsee
Opens a player's inventory.

*Usage*:
```
/invsee [player]
```
*Permission*:
```
activecraft.invsee
```

---

### /item
Give: Gives a player an item.
Name: Renames the item a player is holding.
Lore: 
    add: Add a line to the lore of the held item.
    set: Sets the lore of an item.
    clear: Clears the lore of an item.

*Usage*:
```
/item give [item] <amount>
/item name [name]
/item lore add [lore]
/item lore set [lore]
/item lore clear
```
*Aliases*:
```
/i
```
*Permission*:
```
activecraft.item.give
activecraft.item.name
activecraft.item.lore
```

---

### /lastcoords
See the last coordinates a player had before disconnecting or changing worlds.

*Usage*:
```
/lastcoords [player] <world>
```
*Permission*:
```
activecraft.lastcoords
```

---

### /lastonline
See the last time and date a player has logged into the server.

*Usage*:
```
/lastonline [player]
```
*Permission*:
```
activecraft.lastonline
```

---

### /nick
Changes the player's displayed name.

*Usage*:
```
/nick [name]
/nick [player] [name]
```
*Permission*:
```
activecraft.nick.self
activecraft.nick.others
```

---

### /playtime
See the total time a player has spent on the server.

*Usage*:
```
/playtime <player>
```
*Permission*:
```
activecraft.playtime.self
activecraft.playtime.others
```

---

### /playerlist
List all players on the server. Vanished players are not listed unless the player has the permission *"activecraft.vanish.see"*.

*Usage*:
```
/playerlist
```
*Permission*:
```
activecraft.playerlist
```

---

### /profile
Opens a player's profile.

*Usage*:
```
/profile [player]
```
*Permission*:
```
activecraft.profile
```

---

### /realname
Shows all players with a defined nickname.

*Usage*:
```
/realname [nickname]
```
*Permission*:
```
activecraft.realname
```

---

### /sudo
Execute a command as another player.

*Usage*:
```
/sudo [player] [command]
```
*Permission*:
```
activecraft.sudo
```

---

### /suicide
Kill a player.

*Usage*:
```
/suicide <player>
```
*Permission*:
```
activecraft.suicide.self
activecraft.suicide.others
```

---

### /xp
Give xp to a player. Add a "l" to **[amount]** for levels instead of experience points.

*Usage*:
```
/xp set <player> [amount]
/xp add <player> [amount]
/xp clear <player>
```
*Permission*:
```
activecraft.suicide.self
activecraft.suicide.others
```

---

## Server Management
### /acversion
Checks for all ActiveCraft plugins if you have the latest version installed.

*Usage*:
```
/acversion
```
*Permission*:
```
activecraft.version
```

---

### /language
See and change the plugin's main language.

*Usage*:
```
/language <language>
```
*Permission*:
```
activecraft.language.see
activecraft.language.change
```

---

### /lockdown
Closes the server for all players without the permission *"activecraft.lockdown.bypass"*.

*Usage*:
```
/lockdown [state]
```
*Permission*:
```
activecraft.lockdown
activecraft.lockdown.bypass
```

---

### /lockdownbypass
Allow or disallow a player to join during lockdown.

*Usage*:
```
/lockdownbypass [player] [state]
```
*Permission*:
```
activecraft.lockdown.allowbypass
```

---

### /log
See a players executed command.

*Usage*:
```
/log [state]
```
*Permission*:
```
activecraft.log
```

---

### /ping
Pings the server.

*Usage*:
```
/ping
```
*Permission*:
```
activecraft.ping
```

---

### /ram
See the server's available, used and free ram.

*Usage*:
```
/ram
```
*Permission*:
```
activecraft.ram
```

---

### /restart-server
Restart the server with a timer and alarm for all players.

*Usage*:
```
/restart-server <time>
/restart-server cancel
```
*Permission*:
```
activecraft.restart
```

---

## Item Management
### /book
Addpage: Adds a new page to the book.
Editpage: Edit an existing page.
Title: Change the book's title.
Author: Changes the book's author
Generation: Changes the book's generation

*Usage*:
```
/book addpage [text]
/book editpage [page] [text]
/book title [title]
/book author [author]
/book generation [generation]
```
*Permission*:
```
activecraft.book
```

---

### /enchant
Glint: Applies a glint without applying any enchantments.
Clear: Removes all enchantments from the item.

*Usage*:
```
/enchant [enchantment] <level>
/enchant glint [state]
/enchant clear
```
*Permission*:
```
activecraft.enchant
```

---

### /leathercolor
Changes the color of a held leather item.

*Usage*:
```
/leathercolor [color]
```
*Permission*:
```
activecraft.leathercolor.vanilla
activecraft.leathercolor.hex
```

---

### /more
Fills the item in a players hand to the maximum or a defined amount.

*Usage*:
```
/more <amount>
```
*Permission*:
```
activecraft.more
```

---

### /bow
Multiple bows with arrow hit effects.

*Usage*:
```
/bow [effect]
```
*Permission*:
```
activecraft.bow.[effect]
activecraft.bow.[effect_trigger]
```

---

### /opitems
Gives a player a selection of very overpowered items.

*Usage*:
```
/opitems [items]
```
*Permission*:
```
activecraft.opitems
```

---

### /repair
Fully repairs the item a player is holding.

*Usage*:
```
/repair
```
*Permission*:
```
activecraft.repair
```

---

### /skull
Gives a player their own or another players skull.

*Usage*:
```
/skull <player> <amount>
```
*Permission*:
```
activecraft.skull.self
activecraft.skull.others
activecraft.skull.multiple
```

---

### /tablemenu
Opens a gui with all working stations.

*Usage*:
```
/tablemenu
```
*Aliases*:
```
/craftingtable
/anvil
/enchantingtable
/cartographytable
/grindstone
/loom
/smithingtable
/stonecutter
```
*Permission*:
```
activecraft.tablemenu
```

---

## World Management
### /break
Breaks the block in front of a player.

*Usage*:
```
/break <player>
```
*Permission*:
```
activecraft.break.self
activecraft.break.others
```

---

### /drain
Removes water/lava in the given area.

*Usage*:
```
/drain [radius] <remove_waterlogged> <apply_physics>
```
*Permission*:
```
activecraft.drain
```

---

### /editsign
Toggle sign edit mode. Shift + Rightclick to edit an existing sign.

*Usage*:
```
/editsign <player>
```
*Permission*:
```
activecraft.editsign.self
activecraft.editsign.others
```

---

### /weather
Changes the weather in the player's world.

*Usage*:
```
/weather [state]
```
*Permission*:
```
activecraft.weather
```

---

## Entity Management
### /butcher
Kills all hostile monsters.

*Usage*:
```
/butcher
```
*Permission*:
```
activecraft.butcher
```

---

### /summon
Summons an entity at a player's location.

*Usage*:
```
/summon [entity] <amount>
/summon [player] [entity] <amount>
```
*Permission*:
```
activecraft.summon.self
activecraft.summon.self.multiple
activecraft.summon.others
activecraft.summon.others.multiple
```

---

### /spawner
Gives the player an entity spawner.

*Usage*:
```
/spawner [entity]
/spawner [player] [entity]
```
*Permission*:
```
activecraft.spawner.self
activecraft.spawner.others
```

---

## Other Commands
### /commandstick
Gives the player a stick with a configurable command.

*Usage*:
```
/commandstick [command]
```
*Aliases*:
```
/cmdstick [command]
```
*Permission*:
```
activecraft.commandstick
```

---

### /explode
Creates an explosion at a player's location.

*Usage*:
```
/explode <power> <createfire> <breakblocks>
/explode [player] <power> <createfire> <breakblocks>
```
*Permission*:
```
activecraft.explode.self
activecraft.explode.others
```

---

### /fireball
Creates a fireball at a player's location.

*Usage*:
```
/fireball [power] <createfire>
```
*Permission*:
```
activecraft.fireball
```

---

### /firework
Creates fireworks at a player's location.

*Usage*:
```
/firework <amount> <delay_in_ticks>
```
*Permission*:
```
activecraft.firework
```

---

### /knockbackstick
Gives a player a stick with a high knockback enchantment.

*Usage*:
```
/knockbackstick <player>
```
*Permission*:
```
activecraft.knockbackstick.self
activecraft.knockbackstick.others
```

---

### /strike
Strike a player with lightning.

*Usage*:
```
/strike [player]
```
*Permission*:
```
activecraft.strike.self
activecraft.strike.others
```

{: .label .label-yellow }