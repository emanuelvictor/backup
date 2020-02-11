package design.patterns.proxy.fragment;

/**
 * Created by emanuelvictor on 29/10/15.
 */
class ProxyFragment1 implements ProxyFragment {

    public boolean isShowing() {
        return true;
    }

    @Override
    public void handlerUpdatePosition() {
        System.out.println("Exibindo fragmento 1");
    }
}
