package clone.asiana.asiana_clone.booking.mapper;

import clone.asiana.asiana_clone.booking.dto.BookingSearchResultDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BookingMapper {

    List<BookingSearchResultDTO> searchFlights();
}
