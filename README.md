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
PlayerAuthEvent - Fires whenever a player logs in with the proper password!
Variables:
```
public Player getPlayer() {
  return player;
}

public Boolean isAuthorized() {
  return isAuth;
}
```
