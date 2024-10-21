package GameObjects;
import GameFlow.GameLevel;
import GameInterfaces.Collidable;
import GameInterfaces.Sprite;
import GameObjects.Features.Velocity;
import Geometrics.Point;
import Geometrics.Rectangle;
import biuoop.DrawSurface;
import biuoop.GUI;
import java.awt.Color;
/**
 * @author Hadar Padael.
 * 212916753
 * a class which contains all actions and parameters of Paddle, which is both a Collidable and a Sprite.
 */
    public class Paddle implements Sprite, Collidable {
    //class properties:
        private final biuoop.KeyboardSensor keyboard;
        private final GUI gui;
        private Rectangle rect;
        private final int marginsSize;
        private final Color color;
        private int paddleSpeed;

    /**
     * constructor method.
     * @param gui GUI.
     * @param rect Rectangle.
     * @param color Color.
     * @param marginsSize int.
     */
        //constructor:
        public Paddle(GUI gui, Rectangle rect, Color color, int marginsSize, int paddleSpeed) {
            this.gui = gui;
            this.rect = rect;
            this.marginsSize = marginsSize;
            this.color = color;
            keyboard = gui.getKeyboardSensor();
            this.paddleSpeed = paddleSpeed;
        }

        //accessor:

    /**
     * accessor.
     * @return Recrangle.
     */
    public Rectangle getCollisionRectangle() {
        return rect;
    }

        //other methods:

    /**
     * a method which defines the paddles movement to the left.
     */
        public void moveLeft() {
            if (keyboard.isPressed(keyboard.LEFT_KEY) && (rect.getUpperLeft().getX()
                    > marginsSize + paddleSpeed + 1)) {
                this.rect = new Rectangle(new Point(rect.getUpperLeft().getX() - paddleSpeed,
                        rect.getUpperLeft().getY()),
                        rect.getWidth(), rect.getHeight());
            }
        }
    /**
     * a method which defines the paddles movement to the right.
     */
        public void moveRight() {
            if (keyboard.isPressed(keyboard.RIGHT_KEY) && (rect.getUpperLeft().getX() + rect.getWidth()
                    < (this.gui.getDrawSurface().getWidth() - (this.marginsSize + paddleSpeed + 1)))) {
                this.rect = new Rectangle(new Point(rect.getUpperLeft().getX() + paddleSpeed, rect.getUpperLeft().getY()),
                        rect.getWidth(), rect.getHeight());
            }
        }

        // Sprite
    /**
     * an implementation of Sprite interfaces method.
     * activated movement l/r.
     */
        public void timePassed() {
            moveLeft();
            moveRight();
        }
    /**
     * a method for drawing the block on the surface.
     * @param d Surface.
     */
        public void drawOn(DrawSurface d) {
            int i = (int) Math.round(rect.getUpperLeft().getX()),
                    i1 =  (int) Math.round(rect.getUpperLeft().getY()),
                    i2 = (int) Math.round(rect.getWidth()),
                    i3 = (int) Math.round(rect.getHeight());
                d.setColor(Color.black);
                d.drawRectangle(i, i1, i2, i3);
                d.setColor(this.color);
                d.fillRectangle(i, i1, i2, i3);
        }

        // Collidable

    /**
     * a method which gets the velocity in which the ball will deflect from the paddle,
     * according to specified instructions for "fun paddle".
     * @param collisionPoint Point.
     * @param currentVelocity Velocity.
     * @return Velocity.
     */
        public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
            double xOfPaddle = rect.getUpperLeft().getX();
          double sizeOfRegion = rect.getWidth() / 5;
          double speed = Math.sqrt(Math.pow(currentVelocity.getDx(), 2) + Math.pow(currentVelocity.getDy(), 2));

          if (collisionPoint.getX() <= xOfPaddle + sizeOfRegion) {
              return Velocity.fromAngleAndSpeed(150, speed);
          } else if ((collisionPoint.getX() > sizeOfRegion + xOfPaddle)
                  && ((collisionPoint.getX() <= (2 * sizeOfRegion) + xOfPaddle))) {
              return Velocity.fromAngleAndSpeed(120, speed);
            } else if ((collisionPoint.getX() > 2 * sizeOfRegion + xOfPaddle)
                  && ((collisionPoint.getX() <= 3 * sizeOfRegion + xOfPaddle))) {
              return new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
          } else if ((collisionPoint.getX() > 3 * sizeOfRegion + xOfPaddle)
                  && ((collisionPoint.getX() <= 4 * sizeOfRegion + xOfPaddle))) {
             return Velocity.fromAngleAndSpeed(60, speed);
          } else if ((collisionPoint.getX() > 4 * sizeOfRegion + xOfPaddle)) {
              return Velocity.fromAngleAndSpeed(30, speed);
          }
          return currentVelocity;
        }


    /**
     * a method for adding the ball to the game.
     * @param g Game.
     */
        // Add this paddle to the game.
        public void addToGame(GameLevel g) {
            g.addCollidable(this);
            g.addSprite(this);
        }
    }

