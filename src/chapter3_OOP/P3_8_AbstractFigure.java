package chapter3_OOP;

public abstract class P3_8_AbstractFigure {
    float x; // x-координата точки
    float y; // y-координата точки

    P3_8_AbstractFigure(float x, float y){

        this.x=x;
        this.y=y;
    }
    // абстрактный метод для получения периметра
    public abstract float getPerimeter();
    // абстрактный метод для получения площади
    public abstract float getArea();
}

// производный класс прямоугольника
class Rectangle extends P3_8_AbstractFigure
{
    private float width;
    private float height;

    // конструктор с обращением к конструктору класса Figure
    Rectangle(float x, float y, float width, float height){

        super(x,y);
        this.width = width;
        this.height = height;
    }

    @Override
    public float getPerimeter(){

        return width * 2 + height * 2;
    }

    @Override
    public float getArea(){

        return width * height;
    }
}
