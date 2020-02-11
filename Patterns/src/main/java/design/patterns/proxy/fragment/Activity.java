package design.patterns.proxy.fragment;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by emanuelvictor on 29/10/15.
 */
class Activity {

    public Activity() {
        Set<ProxyFragment> fragments = new HashSet<>(Arrays.asList(new ProxyFragment1(), new ProxyFragment2(), new ProxyFragment3()));

        fragments.forEach(fragment -> fragment.handlerUpdatePosition());
    }

//    public Set<AbstractFragment> getFragments() {
//
//
//    }
}
