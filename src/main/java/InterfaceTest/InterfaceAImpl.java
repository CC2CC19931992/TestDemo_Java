package InterfaceTest;

import org.springframework.stereotype.Service;

/**
 * XXXX
 *
 * @author tc
 * @date 2021/2/2
 */
@Service
public class InterfaceAImpl implements InterfaceA{
    @Override
    public void nofifyAll() {
        System.out.println("！！！！！！");
    }
}
