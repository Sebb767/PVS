package de.fhws.fiw.pvs.rmi;

import java.io.Serializable;

/**
 * Created by proj on 4/19/17.
 */
public class Loop implements Serializable {
    A a;

    public Loop() {
        this.a = new A(this);
    }

    public static class A implements Serializable {
        B b;

        public A(Loop lp) {
            this.b = new B(lp);
        }
    }

    public static class B implements Serializable {
        Loop lp;

        public B(Loop lp) {
            this.lp = lp;
        }
    }
}
