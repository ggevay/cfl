package gg.operators;

import gg.util.TupleIntInt;
import it.unimi.dsi.fastutil.ints.Int2IntMap;
import it.unimi.dsi.fastutil.ints.Int2IntOpenHashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Consumer;

public abstract class GroupBy0ReduceTupleIntInt extends BagOperator<TupleIntInt, TupleIntInt> {

    Int2IntOpenHashMap hm;

    @Override
    public void openOutBag() {
        super.openOutBag();
        hm = new Int2IntOpenHashMap();
        hm.defaultReturnValue(Integer.MIN_VALUE);
    }

    @Override
    public void pushInElement(TupleIntInt e, int logicalInputId) {
        super.pushInElement(e, logicalInputId);

        int g = hm.putIfAbsent(e.f0, e.f1);
        if (g != hm.defaultReturnValue()) {
            reduceFunc(e, g);
        }
    }

    protected abstract void reduceFunc(TupleIntInt e, int g);

    @Override
    public void closeInBag(int inputId) {
        super.closeInBag(inputId);

        hm.int2IntEntrySet().fastForEach(new Consumer<Int2IntMap.Entry>() {
            @Override
            public void accept(Int2IntMap.Entry e) {
                out.collectElement(TupleIntInt.of(e.getIntKey(), e.getIntValue()));
            }
        });

        out.closeBag();
    }
}
