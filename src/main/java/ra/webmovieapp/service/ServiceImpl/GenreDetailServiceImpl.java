package ra.webmovieapp.service.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.webmovieapp.model.entity.GenreDetail;
import ra.webmovieapp.repository.GenreDetailRepository;
import ra.webmovieapp.repository.GenreRepository;
import ra.webmovieapp.repository.MovieRepository;
import ra.webmovieapp.service.GenreDetailService;
@Service
public class GenreDetailServiceImpl implements GenreDetailService {
    @Autowired
    private GenreRepository genreRepository;
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private GenreDetailRepository genreDetailRepository;


    @Override
    public GenreDetail save(Long movieId, Long genreId) {
        GenreDetail genreDetail = GenreDetail.builder ()
                .genre ( genreRepository.getReferenceById ( genreId ) )
                .movie ( movieRepository.getReferenceById ( movieId ) )
                .build ();
        return genreDetailRepository.save ( genreDetail );
    }
}
