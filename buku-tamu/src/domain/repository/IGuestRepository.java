package domain.repository;

import domain.entity.Guest;
import java.util.List;

public interface IGuestRepository {
    Guest addGuest(String name, String purpose);
    List<Guest> getAllGuests();
    List<Guest> searchGuests(String keyword);
    boolean deleteGuest(int id);
}