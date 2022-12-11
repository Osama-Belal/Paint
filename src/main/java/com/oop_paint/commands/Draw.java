package com.oop_paint.commands;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.oop_paint.database.Database;
import com.oop_paint.shapes.Shape;
import com.oop_paint.shapes.ShapeDTO;
import com.oop_paint.shapes.ShapeFactory;

@JsonTypeName("Draw")
@JsonIgnoreProperties("attributes")
public class Draw extends Command{
    private Shape shape;

    public Draw(@JsonProperty("shapeDTO")ShapeDTO data) {
        this.data = data;
    }

    @Override
    public void undo() {
        Database database = Database.getInstance();
        shape.delete();
    }

    @Override
    public void redo() {
        shape.draw();
    }

    @Override
    public void execute() {
        ShapeFactory shapeFactory = new ShapeFactory();
        shape = shapeFactory.getShape(data.className);
        //todo add the dto to the factory
        shape.setX(data.x);
        shape.setY(data.y);
        shape.setColor(data.fill);
        shape.setAttributes(data);
        shape.draw();
        data.id = shape.getId();
    }

    public Shape getShape() {
        return shape;
    }

    public void setShape(Shape shape) {
        this.shape = shape;
    }

    public ShapeDTO getData() {
        return data;
    }

    public void setData(ShapeDTO data) {
        this.data = data;
    }

}
