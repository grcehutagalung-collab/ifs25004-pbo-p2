package usecase;

import domain.entity.Guest;
import domain.repository.IGuestRepository; // <-- Pastikan baris ini ada
import java.util.List;

public class GuestUseCase {
    private final IGuestRepository repository;

    public GuestUseCase(IGuestRepository repository) {
        this.repository = repository;
    }

    public Guest addGuest(String name, String purpose) {
        return repository.addGuest(name, purpose);
    }

    public List<Guest> getAllGuests() {
        return repository.getAllGuests();
    }

    public List<Guest> searchGuests(String keyword) {
        return repository.searchGuests(keyword);
    }

    public boolean deleteGuest(int id) {
        return repository.deleteGuest(id);
    }
}