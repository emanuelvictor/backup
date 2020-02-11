package design.patterns.proxy.fragment;

import design.patterns.proxy.fragment.configuration.Configuration;
import design.patterns.proxy.fragment.configuration.Position;
import design.patterns.proxy.fragment.configuration.Scale;

/**
 * Created by emanuelvictor on 29/10/15.
 */
class ProxyFragment3 implements ProxyFragment {

    private boolean isShowing;

    public boolean isShowing() {
        return isShowing;
    }

    @Override
    public void handlerUpdatePosition() {
        if (Configuration.getInstance().getPosition().equals(Position.LANDSCAPE) &&
                Configuration.getInstance().getScale().equals(Scale.TABLET)) {
            System.out.println("Exibindo fragmento 3");
            this.isShowing = true;
        }else{
            this.isShowing = false;
        }
    }
}
