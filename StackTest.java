import org.junit.Test;
import static org.junit.Assert.assertTrue;

public class StackTest {

    @Test
    public void testEmptyStack() {
      Stack<Integer> stack = new Stack<Integer>();
      boolean empty = stack.isEmpty();
      assertTrue(empty);
    }
  
  }
