package clone.asiana.asiana_clone.booking.service;

import clone.asiana.asiana_clone.booking.dto.BookingSearchResultDTO;
import clone.asiana.asiana_clone.booking.mapper.BookingMapper;
import clone.asiana.asiana_clone.booking.vo.SearchFlightVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingService {


    @Autowired
    BookingMapper mapper;

    public List<BookingSearchResultDTO> SearchFlights(SearchFlightVO Search){

        return mapper.searchFlights();
    }


}
