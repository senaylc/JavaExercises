import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * With this object, you can store information of position like x, y, width, height of your object.
 * Also you can store image of your object and render it.
 */
public class GameObject {
    private Image image;
    private double x;
    private double y;
    private double width;
    private double height;
    private double velocityX;
    private double velocityY;

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getVelocityX() {
        return velocityX;
    }

    public void setVelocityX(double velocityX) {
        this.velocityX = velocityX;
    }

    public double getVelocityY() {
        return velocityY;
    }

    public void setVelocityY(double velocityY) {
        this.velocityY = velocityY;
    }

    /**
     * Check whether this object collides with another object.
     *
     * @param other the other object to check collision with
     * @return true if objects collide, false otherwise
     */
    public boolean collidesWith(GameObject other) {
        if (x + width >= other.getX() && x <= other.getX() + other.getWidth() && y + height >= other.getY() && y <= other.getY() + other.getHeight()) {
            return true;
        }
        return false;
    }

    /**
     * Render this object on the provided graphics context.
     *
     * @param graphicsContext the graphics context to render on
     */
    public void render(GraphicsContext graphicsContext) {
        if (image != null) {
            graphicsContext.drawImage(image, x, y, width, height);
        }
    }

    /**
     * Update this object's position based on its velocity.
     */
    public void updatePosition() {
        x += velocityX;
        y += velocityY;
    }
}
