package gg;

import gg.jobs.*;
import org.apache.flink.runtime.client.JobCancellationException;
import org.junit.Test;

public class CFLITCase {

    @Test(expected=JobCancellationException.class)
    public void testNoCF() throws Exception {
        NoCF.main(null);
    }

    @Test(expected=JobCancellationException.class)
    public void testSimpleCF() throws Exception {
        SimpleCF.main(null);
    }

    @Test(expected=JobCancellationException.class)
    public void testConnectedComponents() throws Exception {
        ConnectedComponents.main(null);
    }
}