# The Dungeon
## General Description
The proposed project for this term is the design and development of a **roguelike video game**
that integrates captivating boss fights. Titled *The Dungeon*, the game will be meticulously
designed to not only appeal to enthusiasts of the roguelike genre, but also attract and welcome
those unacquainted with this game style. The interest in undertaking this project is rooted in my
profound admiration for the genre's unique blend of **high replayability**, **procedural generation**,
and **deep player engagement**. I am particularly fascinated by how roguelike games such as
*Hades* , *The Binding of Isaac* and *Realm of the Mad God* have elevated boss fights into
memorable and intricate challenges that players anticipate and strategize for, rather than mere
roadblocks. Through *The Dungeon*, I hope to explore these intriguing facets further and
contribute to the ongoing evolution of the roguelike genre.

## Key Features
- Procedurally Generated Dungeons: Every run presents a new, uncharted path to navigate
- Engaging Boss Fights: Bosses will offer unique challenges and require tactical prowess to defeat
- Customizable Character Builds: Players will have the freedom to mold their characters
  based on their preferred playstyle.

## User Stories
- As a user, I want to be able to shoot projectiles that damages enemies
- As a user, I want to be able to collect power-up items in my inventory
- As a user, I want to be able to control the movement of my character
- As a user, I want to be able to enter new levels of the dungeon by entering the gate
- As a user, I want an explanation of the basic controls
- As a user, I want to be able to save my progress one time every run
- As a user, I want to be able to load my save before the run starts
- As a user, I want the enemy difficulty to scale with floor level
- As a user, I want to be able to encounter a boss enemy on certain floors
- (hard maybe) As a user, I want to have an active ability

## Gameplay
- Kill all the enemies to proceed into the next room
- Pick a new power up everytime you enter a new room

## Controls
- WASD to move the character
- SPACE to shoot projectile
- E to see the power ups obtains so far

## Save
- R key to save/load the game
- Save can only be used after the enemies in a room is cleared 
- Save will close the game automatically
- Load can only be used during the first floor when enemies still persist
- Each save file can only be loaded once before it is deleted from the saved data
- There can only be one save file at a time

## To-Do
- (hard maybe) active ability kind of power ups
- player passive stats gain

## Phase 4: Task 2
Tue Aug 08 18:07:47 PDT 2023
Added SPEED BOOST
Tue Aug 08 18:08:05 PDT 2023
Added ATK BOOST
Tue Aug 08 18:08:20 PDT 2023
Added DEF BOOST
Tue Aug 08 18:08:42 PDT 2023
Added ATK BOOST
Tue Aug 08 18:09:02 PDT 2023
Added DEF BOOST
Tue Aug 08 18:09:18 PDT 2023
Added SPEED BOOST
Tue Aug 08 18:09:30 PDT 2023
Added DEF BOOST
Tue Aug 08 18:09:45 PDT 2023
Added ATK BOOST
Tue Aug 08 18:10:10 PDT 2023
Added SPEED BOOST
Tue Aug 08 18:10:36 PDT 2023
Added ATK BOOST
Tue Aug 08 18:10:53 PDT 2023
Added RANGE BOOST
Tue Aug 08 18:11:11 PDT 2023
Added ATK SPD BOOST
Tue Aug 08 18:11:26 PDT 2023
Added DEF BOOST
Tue Aug 08 18:11:43 PDT 2023
Added DEF BOOST


## Phase 4: Task 3
After drawing the UML class diagram for the final product of my project, I noticed a few areas
that I can still improve on. The association arrows pointing towards/away from the Game class 
are too numerous, resulting in a very disorganized relationship between the Game and other 
classes in the project. Given more time, I would do some refactoring to fix this problem.

One way of refactoring that could help this issue is to **add a getter method in GamePanel that 
gets the game object**. This is way some association arrows, such as one from MouseHandler, KeyHandler,
PauseScreen, and SelectionScreen. Those classes simply **access the game object through the getter
method from GamePanel**, therefore not needing to have both Game and GamePanel as part of their association. 
This refactor would effectively remove 4 association arrows pointing towards the Game class, 
therefore making it much more readable.

Another refactoring that doesn't direct relate to the UML diagram to **replace long switch cases with a 
HashMap** or something that behaves similarly. This is not an issue for my current project as the game 
implements a very limited number of power ups. But if there were more power ups added to the pool, 
the switch case (getPowerUp(String name)) could easily exceed the checkstyle method line limit, causing 
issues for the code.
