import java.util.concurrent.locks.*;

class TicketBookingSystem {
    private final int totalSeats;
    private boolean[] seats;
    private final Lock lock;

    public TicketBookingSystem(int totalSeats) {
        this.totalSeats = totalSeats;
        this.seats = new boolean[totalSeats];
        this.lock = new ReentrantLock();
    }

    public boolean bookSeat(int seatNumber, String customerType) {
        lock.lock();
        try {
            if (seatNumber < 0 || seatNumber >= totalSeats || seats[seatNumber]) {
                return false; // Seat already booked or invalid seat number
            }
            seats[seatNumber] = true;
            System.out.println(customerType + " booked seat " + seatNumber);
            return true;
        } finally {
            lock.unlock();
        }
    }
}

class BookingThread extends Thread {
    private TicketBookingSystem system;
    private int seatNumber;
    private String customerType;

    public BookingThread(TicketBookingSystem system, int seatNumber, String customerType, int priority) {
        this.system = system;
        this.seatNumber = seatNumber;
        this.customerType = customerType;
        this.setPriority(priority);
    }

    @Override
    public void run() {
        if (!system.bookSeat(seatNumber, customerType)) {
            System.out.println(customerType + " failed to book seat " + seatNumber + " (Already booked)");
        }
    }
}

public class Main {
    public static void main(String[] args) {
        TicketBookingSystem system = new TicketBookingSystem(10);

        Thread vip1 = new BookingThread(system, 2, "VIP-1", Thread.MAX_PRIORITY);
        Thread vip2 = new BookingThread(system, 2, "VIP-2", Thread.MAX_PRIORITY);
        Thread regular1 = new BookingThread(system, 2, "Regular-1", Thread.NORM_PRIORITY);
        Thread regular2 = new BookingThread(system, 3, "Regular-2", Thread.NORM_PRIORITY);
        Thread vip3 = new BookingThread(system, 3, "VIP-3", Thread.MAX_PRIORITY);

        vip1.start();
        vip2.start();
        regular1.start();
        regular2.start();
        vip3.start();
    }
}
