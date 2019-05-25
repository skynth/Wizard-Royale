Wizard Royale README
Roee Haiby, Leo Feng, Sky Ng-Thow-Hing

Introduction : 
You, a powerful wizard, have been put in a battle arena with up to 4 other players. Strategically dispose of your opponents until you are the last wizard standing. Avoid enemy projectiles and pick up spells, powerups, as well as state of the art equipment located in random locations around the map. Explore the map and think of revolutionary strategies in order to survive and emerge victorious. In addition, This game is designed for all individuals over the age of 6, as the game includes some mild violence in forms of combat. This game is especially enjoyable for those who enjoy battle royale formatted games, and fun games where you can compete against your friends. 

Play Wizard Royale today and see if you have what it takes to become the best!

Instructions : 
Click on CONTROLS to view the game controls screen
Click on CREATE SERVER to create a new server so others can join.
Click on JOIN SERVER to join an existing server.

In game controls : 
WASD to move your character up, left, down, and right respectively
Mouse the mouse to aim your projectile and click to shoot it
Walk over an item to pick it up/use it

Features list : 

Must-Haves : 
A player controlled character which moves around the map, fires projectiles
Multiplayer server that players can join, play against each other (5 player max)
Players can win or lose through defeating other players (Last player standing wins)
User friendly, creative graphics for map and players (not simple geometric shapes)
Map that supports intuitive combat between players (includes obstacles)

Want-To-Have features : 
Players can pick up items around the map that increase their ability to defeat other players
Players have a health bar that decreases as their health goes down
Assigns a different color to each wizard in the game as well as displays each user�s username
Each spell or item is creative and unique, it serves a different purpose in the game
User interface that shows each item that they possess, and how many other players there are left
 A list of remaining players in game graphically displayed
Sound effects which improve user experience 

Stretch features :
Single player option which does not require other players to join
Different maps which vary graphically and in features such as obstacles 
Savable user account that tracks wins and personal achievements, etc.
A team mode where players can play 3v3
3D graphics for all aspects 

Class List : 
Package : GameElements
Player - represents each wizard character in the game (Has-a relationship with item class)
Tile (represents a rectangular wall in the map the player is unable to move through)
Projectile(An instance of the projectile that the player shoots)
GameObject (Abstract superclass of all objects in the game)
Consumable (represents all items that the player can pick up on the map)

Package : Window
Window : Starts a new game, generates the window
Wizard Royale : Represents an instance of a single match, determining victory and dictating rules
Instruction Panel: Panel that holds the instructions to the game
Main Menu panel: Initial window with access to instruction and game panels through buttons
WinScreenPanel : the panel for the screen that appears when a player wins
Handler:  handles all changes made to all game objects while users are playing the game.
Key Input: Dictates the key inputs
Mouse Input: Dictates the mouse inputs
ID : holds the IDs for all the objects in our game
Camera : moves the map and its elements with the player so that the game is centered on the player
BufferedImageLoader : creates a bufferedimage out of a image file

Responsibilities: 
Roee: Player, Obstacle, Game classes 
Leo : Map, Windows classes 
Sky: Projectiles and consumables classes, artwork
Everyone Collaborates on Multiplayer functionality and drawing surface class

Credits:
Sky: 
Consumable class, Projectile Class, Wizard Graphics and animations implementation, Player class, handler class, Wizard Royale class, and Main Menu Panel Class, networking

Leo: 
Game Object class, Player class, Handler Class, Id enum, Instructions panel class, Key and mouse input classes, Main menu Panel class, window class, camera class, Tile class, and Wizard Royale class, networking.

Roee:
 Game Object class, Player class, Wizard royale class, Wizard graphics, consumable class, handler class, projectile class

Graphics:

Wizard
https://opengameart.org/content/wizard-5
Under CC0 domain (No copyright free to public)
Title page background
https://steamtradingcards.fandom.com/wiki/Category:Braveland_Wizard

Background for menu or other screen? Its free...
https://www.shutterstock.com/image-illustration/fight-scene-man-magic-wizard-staff-1282908322?src=eiJZx-2xxqY3WFt-RClTrw-1-32

Walls and floor :
https://opengameart.org/content/dungeon-tileset

Fireball : Crawltiles tileset
https://opengameart.org/content/dungeon-crawl-32x32-tiles
