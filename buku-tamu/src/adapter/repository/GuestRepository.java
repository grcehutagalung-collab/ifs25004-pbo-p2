package adapter.repository;

import domain.entity.Guest;
import domain.repository.IGuestRepository; // <-- Import yang benar
import java.util.ArrayList;
import java.util.List;

public class GuestRepository implements IGuestRepository {
    private final List<Guest> database = new ArrayList<>();
    private int idCounter = 1;

    @Override
    public Guest addGuest(String name, String purpose) {
        Guest guest = new Guest(idCounter++, name, purpose);
        database.add(guest);
        return guest;
    }

    @Override
    public List<Guest> getAllGuests() {
        return database;
    }

    @Override
    public List<Guest> searchGuests(String keyword) {
        List<Guest> results = new ArrayList<>();
        for (Guest guest : database) {
            if (guest.getName().toLowerCase().contains(keyword.toLowerCase())) {
                results.add(guest);
            }
        }
        return results;
    }

    @Override
    public boolean deleteGuest(int id) {
        return database.removeIf(guest -> guest.getId() == id);
    }
}