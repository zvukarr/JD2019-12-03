package by.it.shpakovskiy.jd02_02;

public class Buyer extends Thread implements IBuyer, IUseBasket {
    private boolean pensioner;
    private BulbaStore store;
    private Basket basket;
    private Product product;
    private double check;

    Buyer(int num, BulbaStore store) {
        super("Buyer-" + num);
        this.store = store;
        pensioner = Math.random() < 0.25;
        start();
    }

    public void setCheck(double check) {
        this.check = check;
    }

    public double getCheck() {
        return check;
    }

    @Override
    public boolean enterToMarket() {
        int c = store.enter(this);
        if (c >= 0) {
            if (!ShopRunner.IN_A_TABLE) System.out.println(this + " enter to the BulbaStore. Count buyer: " + c);
            yield();
            return true;
        } else {
            if (c == -1) if (!ShopRunner.IN_A_TABLE) System.err.println(this + " was not allowed into the store.");
            return false;
        }
    }

    @Override
    public void takeBasket() {
        if (!ShopRunner.IN_A_TABLE) System.out.println(this + " waits basket");
        while (true) {
            basket = store.getBasket();
            if (basket != null) {
                break;
            }
            yield();
        }
        if (!ShopRunner.IN_A_TABLE) System.out.println(this + " take basket");
        sleepBuyer(500, 2000);
    }

    @Override
    public void chooseGoods() {
        if (!ShopRunner.IN_A_TABLE) System.out.println(this + " started choosing by goods");
        product = store.getAccessToTheShowcase().getSomeProduct();
        sleepBuyer(125, 500);
        if (!ShopRunner.IN_A_TABLE) System.out.println(this + " finished choosing by goods");

    }

    @Override
    public void putGoodsToBasket() {
        basket.putGoodsToTheBasket(product);
        if (!ShopRunner.IN_A_TABLE)
            System.out.println(this + " put " + product + " to the basket. Total goods count in basket: " + basket.getSize());
        sleepBuyer(125, 500);
    }

    private void getInQueue() {
        int size = store.addToQueue(this);
        if (!ShopRunner.IN_A_TABLE) System.out.println(this + " stay in queue. Buyers count in queue: " + size);
        synchronized (this) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    Basket getGoods() {
        return basket;
    }

    @Override
    public void goToOut() {
        store.returnBasket(basket);
        int count = store.leave(this);
        if (!ShopRunner.IN_A_TABLE) System.out.println(this + " go out. Your check:" + check + " Left: " + count);
    }

    @Override
    public void run() {
        if (enterToMarket()) {
            takeBasket();
            for (int i = 0; i < Helper.getRandom(1, 4); i++) {
                chooseGoods();
                putGoodsToBasket();
            }
            getInQueue();
            goToOut();
        }
    }

    @Override
    public String toString() {
        return (pensioner ? "P " : "  ") + this.getName();
    }

    String getShortName() {
        return this.getName();
    }

    private void sleepBuyer(int from, int to) {
        if (pensioner) to=(int)(to*1.5);
        Helper.sleepRandom(from,to);
    }

    public boolean isPensioner() {
        return pensioner;
    }
}
