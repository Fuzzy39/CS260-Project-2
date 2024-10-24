package data;
import javafx.geometry.*;
import javafx.scene.paint.Color;

public record MemoRecord (
	int ID,
	Point2D location,
	String note,
	Color foregroundColor,
	Color backgroundColor)
{}
