package general.store;

import general.State;
import general.GeneralEvent;

import java.util.Observable;

public class StoreState extends State {
    // Simulation parameters
    // alla dessa ska komma via parametrar till konstruktor
    protected final int cashRegisters = 4;
    public final int maxCustomers = 50;
    protected final double arriveInterval = 1; // num of customers an "hour"
    protected final double cashierMin = 2;
    protected final double cashierMax = 4;
    protected final double pickingMin = 1;
    protected final double pickingMax = 5;
    protected final int openingTime = 0;
    public final int randomizerSeed = 1234;

    // Results
    private int buyers;
    private int missedBuyers;
    private int cashRegisterDowntime;
    private int queueTime;

    private int missedCustomers = 0;


    // Other
    public CashRegisterQueue cQueue = new CashRegisterQueue();
    public CustomerCreator cCreator = new CustomerCreator();
    private int currentAmountCustomers = 0;
    public double currentTime;

    public boolean isOpen;
    public int currentCustomers;

    public int freeCashRegisters = cashRegisters;
    CustomerCreator customerCreator;

    public ArriveIntervalCalculator arrive;
    public CashierSpeedCalculator cashierSpeed;
    public PickingTimeCalculator pickingTime;
    public StoreState() {
        arrive = new ArriveIntervalCalculator(arriveInterval, randomizerSeed);
        cashierSpeed = new CashierSpeedCalculator(cashierMin, cashierMax, randomizerSeed);
        pickingTime = new PickingTimeCalculator(pickingMin, pickingMax, randomizerSeed);


        System.out.println(arrive.getTime());
        System.out.println(cashierSpeed.getTime());
        System.out.println(pickingTime.getTime());

        customerCreator = new CustomerCreator();
    }

    public Customer newCustomer() {
        this.setChanged();
        this.notifyObservers();
        return customerCreator.getCustomer();
    }

    public void closeRegister() {
        if (freeCashRegisters > 0) {
            freeCashRegisters--;
        }
        else {
            // Throw error eller nåt?
        }
    }

    public void openRegister() {
        if (freeCashRegisters < cashRegisters) {
            freeCashRegisters++;
        }
        else {
            // Throw error eller nåt?
        }
    }

    public void missedCustomer() {
        this.missedCustomers += 1;
    }

    public void updateView() {
        setChanged();
        notifyObservers();
    }

}
