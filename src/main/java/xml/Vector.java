package xml;

import lombok.experimental.FieldNameConstants;

@FieldNameConstants
public class Vector {
    public String width;
    public String height;
    public String viewportWidth;
    public String viewportHeight;

    public Vector(String width, String height, String viewportWidth, String viewportHeight) {
        this.width = width;
        this.height = height;
        this.viewportWidth = viewportWidth;
        this.viewportHeight = viewportHeight;
    }
}
