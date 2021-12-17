---
layout: page
title: Configure the plugin
permalink: /config/
nav_order: 3
---

# Configure ActiveCraft-Core

---

# Default config.yml
```yaml
# Ingame Visual formats
chat-format: '%displayname%§f: %message%'
join-format: §b» §f%displayname%
quit-format: §c« §f%displayname%

# Misc settings
use-timer-on-tpa: true 
mute-new-players: false 
remove-default-mute-after: 10 
check-for-matching-ips: true 
socialspy-to-console: false 
language: de 
announce-afk: true 

# Vanish settings
enable-vanish-format: true 
vanish-format: '[V]' 

# Lockdown Settings
lockdown-modt: This server is in lockdown mode. You are not able to join until this is disabled. 
lockdown-kick-message: This server is in lockdown mode. You are not able to join until this is disabled. 

# Clear In Tab Complete
hide-commands-after-plugin-name: 
  enable: true 
  except: 
    - minecraft:enchant 
  hide-commands: 
    - command 
```

---

## chat-format
Configure the appearance of the server's chat. 

*Placeholders*:
```
%displayname%
%playername%
%message%
```

---

## join-format
Configure the appearance of the server's join messages.

*Placeholders*:
```
%displayname%
%playername%
```

---

## quit-format
Configure the appearance of the server's leave messages.

*Placeholders*:
```
%displayname%
%playername%
```

---

## use-timer-on-tpa
If the TPA command should teleport instantly or after a delay of 3 seconds.

*Values*:
```
true / false
```

---

## mute-new-players
Configure the appearance of the server's join messages.

*Placeholders*:
```
%displayname%
%playername%
```

---

## remove-default-mute-after
The time in minutes after which a new player will be able to speak again.

*Values*:
```
1, 2, 3,...
```

---

## check-for-matching-ips
If enabled, all players with the permission **"activecraft.matchingip.notify"** will receive a message, if two players share the same IP.

*Values*:
```
true / false
```

---

## socialspy-to-console
If enabled, direct messages are logged in the console.

*Values*:
```
true / false
```

---

## language
Defines the plugin language.

*Values*:
```
en
de
sv
```

---

## announce-afk
If enabled, a message is sent when a player performs the /afk command.

*Values*:
```
true / false
```

---

## enable-vanish-format
If enabled, a tag is added to a vanished player's name. 

*Values*:
```
true / false
```

---

## enable-vanish-format
Defines the tag, which is added to a vanished player's name. 

*Values*:
```
Anything
```

---

## lockdown-modt
The modt displayed in the server list while the server is in lockdown mode.

*Values*:
```
Anything
```

---

## lockdown-kick-message
The kick message players get when kicked through the activation of lockdown mode.

*Values*:
```
Anything
```

---

## hide-commands-after-plugin-name
Hides the commands that start with **"plugin:"**.

### enable

*Values*:
```
true / false
```

### except

*Values*:
```
Any Command or plugin name (for ex.: minecraft:enchant, activecraft-core)
```

---

## hide-commands
Ignore this. It has no functionality as of right now.