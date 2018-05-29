package taxation;

import java.util.List;

public class Accountant {
    public static void doTaxes(Taxable t){

    }

    public static void collectJoesClients(List<? super Individual> clientList) {
        Individual i1 = null;
        Individual i2 = null;
        clientList.add(i1);
        clientList.add(i2);
    }

    public static void doBatchTaxes(List<? extends Taxable> manyTaxable) {
        for (Taxable t : manyTaxable) {
            doTaxes(t);
        }
//        Corporation c = null;
//        manyTaxable.add(c);
    }

    public static void doBatchTaxes(Taxable[] manyTaxable) {
        for (Taxable t : manyTaxable) {
            doTaxes(t);
        }
        Corporation c = null;
        manyTaxable[0] = c;
    }

    public static void main(String[] args) {
        Taxable t = null;
        doTaxes(t);
        Individual i = null;
        doTaxes(i);

        List<Taxable> clientList = null;
        doBatchTaxes(clientList);

        Individual[] joesClientArray = {};
        doBatchTaxes(joesClientArray);

        List<Individual> joesClients = null;
        doBatchTaxes(joesClients);

        List<Object> lo = null;
        collectJoesClients(lo);
        collectJoesClients(clientList);
        collectJoesClients(joesClients);

        List<RichIndividual> lri = null;
        collectJoesClients(lri);
    }
}
