package designPattern.chainofresponsibility;

public class DivideOperator extends AbstractOperator{
  
  @Override
  public int calculate(String op, int a, int b) {
    if (op.equals("/")) 
      return a/b;
    return super.calculate(op, a, b);
  }
  
}
