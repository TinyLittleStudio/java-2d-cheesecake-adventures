package eu.hackathon.game.external.entity.interactables;

import eu.hackathon.cheesecake.internal.graphics.ExtendedGraphics;
import eu.hackathon.game.GameHandler;
import eu.hackathon.game.external.entity.Entity;
import eu.hackathon.game.external.entity.Interactables;
import eu.hackathon.game.external.entity.Player;
import eu.hackathon.game.utils.Resources;
import eu.hackathon.game.utils.external.ActionLog;
import eu.hackathon.game.utils.external.Location;
import eu.hackathon.game.utils.external.Material;
import eu.hackathon.game.utils.internal.Animation;

import java.util.Random;

public class Person extends Interactables {
    private Animation animation = new Animation(-Entity.SQR_SIZE, -Entity.SQR_SIZE, Resources.TILESET[48], Resources.TILESET[49]);

    public Person() {
        super(Material.PERSON);
        animation.setVelocity(400.0f);
    }

    @Override
    public void init() {
        if (GameHandler.instance().getLevel() != null) {
            Location location = GameHandler.instance().getLevel().getLocationOf(this);

            animation.setLocation(location.getX(), location.getY());
        }
        animation.update();
    }

    @Override
    public int interact(Player player) {
        Resources.COUGH.play();

        if (player.getInventory() == player.getLevel().getKey()) {
            player.clearInventory();

            ActionLog.request("Thank you!", 5000);

            int index = -1;
            boolean isFinished = false;

            while (!isFinished) {
                index = new Random().nextInt(player.getLevel().getWidth() * player.getLevel().getHeight());

                if (player.getLevel().getEntity(index) == null) {
                    isFinished = true;
                }
            }

            if (index != -1) {
                player.getLevel().getEntities()[index] = Material.KEY.instantiate();
            }
            return -1;
        }

        if (player.getInventory() != -1) {
            ActionLog.request("I don't need that!", 5000);
        } else {
            ActionLog.request(new Random().nextBoolean() ? "Cheesecake!" : "*Cough*", 5000);
        }
        return -1;
    }

    @Override
    public void tick() {
        if (GameHandler.instance().getLevel() != null) {
            Location location = GameHandler.instance().getLevel().getLocationOf(this);

            animation.setLocation(location.getX(), location.getY());
        }
        animation.tick();
    }

    @Override
    public void draw(ExtendedGraphics extendedGraphics) {
        animation.draw(extendedGraphics);
    }
}

