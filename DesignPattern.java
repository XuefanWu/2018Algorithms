1. Factory
Using Factory design pattern we can create object without exposing creation logic to client
public interface Shape{
    void draw();
}
//-------
public class Rectangle implements Shape{
    @Override
    public void draw(){
        System.out.println("rec");
    }
}
public class Circle implements Shape{
    @Override
    public void draw(){
        System.out.println("cir");
    }
}
//------------
public class ShapeFactory{
    public Shape getShape(String s){
        if(s == null) return null;
        if(s.equals("Rectangle")){
            return new Rectangle();
        }
        else if(s.equals("Circle")){
            return new Circle();
        }
        return null;
    }
}

//-----
public void main(String[] args){
    ShapeFactory sf = new ShapeFactory();
    Shape s1 = sf.getShape("Circle");
    s1.draw();
    Shape s2 = sf.getShape("Rectangle");
    s2.draw();
}


2. Abstract Factory
factory of Factory


3. Decorator
//using decorator design pattern, we can add a feature to an interface/existing object without change its structure
 public interface Shape{
    void draw();
}
//-------
public class Rectangle implements Shape{
    @Override
    public void draw(){
        System.out.println("rec");
    }
}
public class Circle implements Shape{
    @Override
    public void draw(){
        System.out.println("cir");
    }
}
//------------
public abstract class ShapeDecorator implements Shape{
    protected Shape decoratedShape;
    public ShapeDecorator(Shape shape){
        this.decoratedShape = shpe;
    }
    public void draw(){
        decoratedShape.draw();
    }
}
//======
public class RedDec extends ShapeDecorator{
    public RedDec(Shape s){
        super(s);
    }
    @Override
    public void draw(){
        decoratedShape.draw();
        setRedBoarder();
    }
    private void setRedBoarder(){
        System.out.println("red");
    }
}
//-----
public void main(String[] args){
    ShapeFactory sf = new ShapeFactory();
    Shape s1 = new Circle();  
    s1.draw();                          //cir
    Shape s2 = new Rectangle();
    s2.draw();                          //rec
    Shape redS1 = new RedDec(s1);
    redS1.draw();                       //cir
                                        //red
    Shape redS2 = new RedDec(s2);
    redS2.draw();                       //rec
                                        //red
}

4.Strategy 
//strategy is like a factory of making methods, we encapsulate the implement of detailed method
public interface Strategy{
    public int doOp(int num1, int num2);
}
//----
public class Add implements Strategy{
    @Override
    public int doOp(int num1, int num2){
        return num1+num2;
    }
}
public class Multiply implements Strategy{
    @Override
    public int doOp(int num1, int num2){
        return num1*num2;
    }
}
//----
public class system{
    private Strategy s;
    public setStrategy(Strategy s){
        this.s = s;
    }
    public int doOperation(int num1, int num2){
        s.doOp(num1,num2);
    }
}
//----
public static void main(String[] args){
    system s = new system();
    s.setStrategy(new Add());
    s.doOperation(1,2);
}


5. State
//allow a class behaviour change based on its state and switch between states
public interface AbstractState{
    public void selectItem(String selection);
    public void insertMoney(int value);
    //Here we put all the functions which will be affected when state changes
}
//------
public class NoSelectionState implements AbstractState{
    VendingMachine vendingMachine;
    public NoSelectionState(VendingMachine v){
        vendingMachine = v;
    }

    @Override
    public void selectItem(String selection){
        vendingMachine.setSelectItem(selection);
        vendingMachine.ChangeToHasSelectionState();
    }
    @Override
    public void insertMoney(int value){
        System.out.println("Please make a selection first");
    }
}
//----
public class VendingMachine{
    private AbstractState state;
    private NoSelectionState noSelectionState;
    public String selectedItem;
    
    public VendingMachine(){
        noSelectionState = new NoSelectionState(this);
        state = noSelectionState;
    }

    private void ChangeToHasSelectionState(){
        state = hasSelectionState;
    }
    public void selectedItem(String selection){
        state.selectedItem(selection);
    }
    public void insertMoney(int value){
        state.insertMoney(value);
    }


    public void setSelectItem(String s){
        this.selectedItem = s;
    }
    
}