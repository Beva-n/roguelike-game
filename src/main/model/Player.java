package model;

// represents a player with parameters from entity and
// hp/defense/attack/shoot cooldown/range and face direction
public class Player extends Entity {

    private int maxHealth = 100;
    private int health;
    private int maxDefense = 0;
    private int defense;
    private int attack;
    private int maxAttack = 20;
    private int shootCd = 0;
    private int maxShootCd = 15;
    private int range = 40;
    private int speed = 3;

    //Effects: Constructs a player with hp/max hp of 100, attack/max attack of 20
    //         def/ max def of 0, a range of 40, a shootcd of half a second, and a fixed starting position
    public Player(Game game) {
        super(new Position(60, 232), game);
        this.health = maxHealth;
        this.defense = maxDefense;
        this.attack = maxAttack;
    }

    //Modifies: this
    //Effects: reset the player to default position
    public void reset() {
        setPosition(new Position(60, 232));
        // this.defense = maxDefense;
        // this.attack = maxAttack;
    }

    //Modifies: this
    //Effects: Reduces the player's shoot cool down by 1 respectively
    public void update() {
        shootCd--;
    }

    //Modifies: this
    //Effects: moves the player's position by the provided x and y value
    //         if the movement results in collision with a wall, the movement will not take place
    public void move(int x, int y) {
        position.editPosX(x);
        position.editPosY(y);
        if (game.getMap().checkCollisionWall(this)) {
            position.editPosX(-x);
            position.editPosY(-y);
        }
    }

    //Modifies: this
    //Effects: increases the player's speed, attack, health, and defense
    public void scaleWithLevel() {
        editSpeed(1);
        editAttack(7);
        editHealth(30);
        heal(30);
        editDefense(4);
    }

    //Requires: level >= 1
    //Effects: increases the player's overall stats based on the level parameter
    public void scaleWithLevel(int level) {
        int count = (level - (level % 5)) / 5;
        for (int i = 0; i < count; i++) {
            scaleWithLevel();
        }
    }

//    //Modifies: game
//    //Effects: if shoot cooldown is greater than 0, nothing happens
//    //         if shoot cooldwon is less than or equal to 0, spawn a projectile flying towards
//    //         the player's face direction
//    public void shoot() {
//        if (shootCd > 0) {
//            return;
//        }
//        shootCd = maxShootCd;
//        switch (faceDirection) {
//            case "down":
//                game.getProjectileManager().spawn(new Projectile(
//                        new Position(position.getX(), position.getY() + 1), 's', attack, game));
//                return;
//            case "up":
//                game.getProjectileManager().spawn(new Projectile(
//                        new Position(position.getX(), position.getY() - 1), 'w', attack, game));
//                return;
//            case "left":
//                game.getProjectileManager().spawn(new Projectile(
//                        new Position(position.getX() - 1, position.getY()), 'a', attack, game));
//                return;
//            default:
//                game.getProjectileManager().spawn(new Projectile(
//                        new Position(position.getX() + 1, position.getY()), 'd', attack, game));
//        }
//    }
//
//    //Modifies: this
//    //Effects: same as entity plus change face direction to right
//    @Override
//    public void moveRight() {
//        super.moveRight();
//        faceDirection = "right";
//    }
//
//    //Modifies: this
//    //Effects: same as entity plus change face direction to left
//    @Override
//    public void moveLeft() {
//        super.moveLeft();
//        faceDirection = "left";
//    }
//
//    //Modifies: this
//    //Effects: same as entity plus change face direction to up
//    @Override
//    public void moveUp() {
//        super.moveUp();
//        faceDirection = "up";
//    }
//
//    //Modifies: this
//    //Effects: same as entity plus change face direction to down
//    @Override
//    public void moveDown() {
//        super.moveDown();
//        faceDirection = "down";
//    }

    //Requires: damage > 0
    //Modifies: this
    //Effects: reduces the player's health by the higher between damage - defense
    //         and 10% of damage
    public void decreaseHealth(int damage) {
        health -= Math.max(damage - defense, damage * 0.1);
    }

    //Modifies: this
    //Effects: change the player's attack and max attack by amount
    public void editAttack(int amount) {
        attack += amount;
        maxAttack += amount;
    }

    //Modifies: this
    //Effects: decreases max shoot cd by amount, cannot be lowered lower than 6 shoots per second
    public void editAttackSpeed(int amount) {
        maxShootCd = Math.max(5, maxShootCd - amount);
    }

    //Modifies: this
    //Effects: change the player's speed by amount
    public void editSpeed(int amount) {
        speed += amount;
    }

    //Modifies: this
    //Effects: change the player's defense and max defense by amount
    public void editDefense(int amount) {
        defense += amount;
        maxDefense += amount;
    }

    //Modifies: this
    //Effects: change the player's max health by amount
    public void editHealth(int amount) {
        maxHealth += amount;
    }

    //Modifies: this
    //Effects: change the player's range by amount
    public void editRange(int amount) {
        range += amount;
    }

    //Modifies: this
    //Effects: increase the player's health by amount, it cannot exceed maxHealth
    public void heal(int amount) {
        health = Math.min(health + amount, maxHealth);
    }

    public void resetShootCd() {
        shootCd = maxShootCd;
    }

    public int getHealth() {
        return health;
    }

    public int getDefense() {
        return defense;
    }

    public int getAttack() {
        return attack;
    }

    public int getRange() {
        return range;
    }

    public int getShootCd() {
        return shootCd;
    }

    public int getMaxShootCd() {
        return maxShootCd;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public double getHealthLost() {
        return 100 * (1.0 - (double) health / (double) maxHealth);
    }

    public int getSpeed() {
        return speed;
    }


}
