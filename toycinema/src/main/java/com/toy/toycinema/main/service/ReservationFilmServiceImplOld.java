package com.toy.toycinema.main.service;//package com.toy.boxoffice.main.service;
//
//import com.toy.boxoffice.dto.*;
//import com.toy.boxoffice.main.mapper.MainPageSqlMapper;
//
//import java.time.LocalDate;
//import java.time.LocalTime;
//import java.util.*;
//import java.util.function.Function;
//
////@Service
//public class ReservationFilmServiceImplOld implements ReservationFilmService {
//    private final MainPageSqlMapper mainPageSqlMapper;
//
//    public ReservationFilmServiceImplOld(MainPageSqlMapper mainPageSqlMapper) {
//        this.mainPageSqlMapper = mainPageSqlMapper;
//    }
//
//    @Override
//    public Map<String, Object> getScreeningSchedule(SelectFilmReservationDto selectFilmReservationDto) {
//
//        Map<String, Object> map = new HashMap<>();
//
////        TheaterDto theaterDto = new TheaterDto();
//        List<TheaterDto> theaterDtoList = new ArrayList<>();
//
////        FilmDto filmDto = new FilmDto();
//        List<FilmDto> filmDtoList = new ArrayList<>();
//
//        List<BoxTypeDto> boxTypeDtoList = new ArrayList<>();
//
//        List<BoxDto> boxDtoList = new ArrayList<>();
//
//        List<FilmPlayingTableDto> filmPlayingTableDtoList = new ArrayList<>();
//
//        filmPlayingTableDtoList = getFilmPlayingTableListByDate(FilmPlayingTableDtoBuilder.builder().date(selectFilmReservationDto.getDate()).build());
//
//        // 3차 gpt 결과물
////                List<FilmDto> filmDtoList
//        filmDtoList = extractDistinctAndFetch(
//                filmPlayingTableDtoList,
//                FilmPlayingTableDto::getFilmId,
//                filmId -> getFilmListByFilmId(FilmDtoBuilder.builder().id(filmId).build())
//        );
//
////                List<BoxTypeDto> boxTypeDtoList;
//        boxTypeDtoList = extractDistinctAndFetch(
//                filmPlayingTableDtoList,
//                FilmPlayingTableDto::getBoxTypeId,
//                boxTypeId -> getBoxTypeListByBoxTypeId(BoxTypeDtoBuilder.builder().id(boxTypeId).build())
//        );
//
////                List<BoxDto> boxDtoList;
//        boxDtoList = extractDistinctAndFetch(
//                boxTypeDtoList,
//                BoxTypeDto::getBoxId,
//                boxId -> getBoxListByBoxId(BoxDtoBuilder.builder().id(boxId).build())
//        );
//
////                List<TheaterDto> theaterDtoList
//        theaterDtoList = extractDistinctAndFetch(
//                boxDtoList,
//                BoxDto::getTheaterId,
//                theaterId -> getTheaterListByTheaterId(TheaterDtoBuilder.builder().id(theaterId).build())
//        );
//
//
//        switch (makeFlag(selectFilmReservationDto)) {
////            case 0b000 -> {
////                filmId = 0, theaterId = 0, date = null
////                예:
////                아무것도 선택 안됨
////            }
////            case 0b001 -> {
////                date만 선택됨
////            }
//            case 0b000, 0b001 -> {
//                // filmId=0, theaterId=0, date=null
//                // filmId=0, theaterId=0, date!=null
//                // 아무것도 선택 안됨
//                // date만 선택됨
//
////                filmPlayingTableDtoList = getFilmPlayingTableListByDate(FilmPlayingTableDtoBuilder.builder().date(selectFilmReservationDto.getDate()).build());
//
//                // 1차
////                for (FilmPlayingTableDto filmPlayingTableDto : filmPlayingTableDtoList) {
//
////                    filmDto.setId(filmPlayingTableDto.getFilmId());
////                    filmDtoList = getFilmListByFilmId(filmDto);
////
////                    BoxTypeDto boxTypeDto = new BoxTypeDto();
////                    boxTypeDto.setId(filmPlayingTableDto.getBoxTypeId());
////
////                    List<BoxTypeDto> boxTypeDtoList = getBoxTypeListByBox(boxTypeDto);
////
////                    for (BoxTypeDto boxTypeDtoForFindBox : boxTypeDtoList) {
////                        BoxDto boxDto = new BoxDto();
////                        boxDto.setId(boxTypeDto.getBoxId());
////                    }
////                }
//
//                // 2차
////                Set<Integer> uniqueSet = new HashSet<>();
////
////                uniqueSet = filmPlayingTableDtoList.stream()
////                        .map(FilmPlayingTableDto::getFilmId)
////                        .collect(Collectors.toSet());
////                for (Integer filmId : uniqueSet) {
////                    filmDtoList = getFilmListByFilmId(FilmDtoBuilder.builder().id(filmId).build());
////                }
////
////                uniqueSet = filmPlayingTableDtoList.stream()
////                        .map(FilmPlayingTableDto::getBoxTypeId)
////                        .collect(Collectors.toSet());
////                for (Integer boxTypeId : uniqueSet) {
////                    boxTypeDtoList = getBoxTypeListByBoxTypeId(BoxTypeDtoBuilder.builder().id(boxTypeId).build());
////                }
////
////                uniqueSet = boxTypeDtoList.stream()
////                        .map(BoxTypeDto::getBoxId)
////                        .collect(Collectors.toSet());
////                for (Integer boxId : uniqueSet) {
////                    boxDtoList = getBoxListByBoxId(BoxDtoBuilder.builder().id(boxId).build());
////                }
////
////                uniqueSet = boxDtoList.stream()
////                        .map(BoxDto::getTheaterId)
////                        .collect(Collectors.toSet());
////                for (Integer theaterID : uniqueSet) {
////                    theaterDtoList = getTheaterListByTheaterId(TheaterDtoBuilder.builder().id(theaterID).build());
////                }
//
////                // 3차 gpt 결과물
//////                List<FilmDto> filmDtoList
////                filmDtoList = extractDistinctAndFetch(
////                        filmPlayingTableDtoList,
////                        FilmPlayingTableDto::getFilmId,
////                        filmId -> getFilmListByFilmId(FilmDtoBuilder.builder().id(filmId).build())
////                );
////
//////                List<BoxTypeDto> boxTypeDtoList;
////                boxTypeDtoList = extractDistinctAndFetch(
////                        filmPlayingTableDtoList,
////                        FilmPlayingTableDto::getBoxTypeId,
////                        boxTypeId -> getBoxTypeListByBoxTypeId(BoxTypeDtoBuilder.builder().id(boxTypeId).build())
////                );
////
//////                List<BoxDto> boxDtoList;
////                boxDtoList = extractDistinctAndFetch(
////                        boxTypeDtoList,
////                        BoxTypeDto::getBoxId,
////                        boxId -> getBoxListByBoxId(BoxDtoBuilder.builder().id(boxId).build())
////                );
////
//////                List<TheaterDto> theaterDtoList
////                theaterDtoList = extractDistinctAndFetch(
////                        boxDtoList,
////                        BoxDto::getTheaterId,
////                        theaterId -> getTheaterListByTheaterId(TheaterDtoBuilder.builder().id(theaterId).build())
////                );
//
//
//            }
//            case 0b010, 0b011 -> {
//                // theaterId만 선택됨
//
//                boxDtoList = getBoxListByTheaterId(BoxDtoBuilder.builder().theaterId(selectFilmReservationDto.getTheaterId()).build());
//
//                boxTypeDtoList = extractDistinctAndFetch(
//                        boxDtoList,
//                        BoxDto::getId,
//                        boxId -> getBoxTypeListByBoxId(BoxTypeDtoBuilder.builder().boxId(boxId).build())
//                );
//
//                filmPlayingTableDtoList = extractDistinctAndFetch(
//                        boxTypeDtoList,
//                        BoxTypeDto::getBoxId,
//                        boxTypeId -> getFilmPlayingTableListByBoxTypeId(FilmPlayingTableDtoBuilder.builder().boxTypeId(boxTypeId).build())
//                );
//
//                filmDtoList = extractDistinctAndFetch(
//                        filmPlayingTableDtoList,
//                        FilmPlayingTableDto::getFilmId,
//                        filmId -> getFilmListByFilmId(FilmDtoBuilder.builder().id(filmId).build())
//                );
//
////            }
////            case 0b011 -> {
//                // theaterId, date만 선택됨
//
////                boxDtoList = getBoxListByTheaterId(BoxDtoBuilder.builder().theaterId(selectFilmReservationDto.getTheaterId()).build());
////
////                boxTypeDtoList = extractDistinctAndFetch(
////                        boxDtoList,
////                        BoxDto::getId,
////                        boxId -> getBoxTypeListByBoxId(BoxTypeDtoBuilder.builder().boxId(boxId).build())
////                );
////
////                filmPlayingTableDtoList = extractDistinctAndFetch(
////                        boxTypeDtoList,
////                        BoxTypeDto::getBoxId,
////                        boxTypeId -> getFilmPlayingTableListByBoxTypeId(FilmPlayingTableDtoBuilder.builder().boxTypeId(boxTypeId).build())
////                );
////
////                filmDtoList = extractDistinctAndFetch(
////                        filmPlayingTableDtoList,
////                        FilmPlayingTableDto::getFilmId,
////                        filmId -> getFilmListByFilmId(FilmDtoBuilder.builder().id(filmId).build())
////                );
//
//            }
//            case 0b100 -> {
//                // filmId만 선택됨
//
//                filmDtoList = getFilmListByFilmId(FilmDtoBuilder.builder().id(selectFilmReservationDto.getFilmId()).build());
//
//                filmPlayingTableDtoList = extractDistinctAndFetch(
//                        filmDtoList,
//                        FilmDto::getId,
//                        filmId -> getFilmPlayingTableListByFilmId(FilmPlayingTableDtoBuilder.builder().filmId(filmId).build())
//                );
//
//                boxTypeDtoList = extractDistinctAndFetch(
//                        filmPlayingTableDtoList,
//                        FilmPlayingTableDto::getBoxTypeId,
//                        boxTypeId -> getBoxTypeListByBoxTypeId(BoxTypeDtoBuilder.builder().id(boxTypeId).build())
//                );
//
//                boxDtoList = extractDistinctAndFetch(
//                        boxTypeDtoList,
//                        BoxTypeDto::getBoxId,
//                        boxId -> getBoxListByBoxId(BoxDtoBuilder.builder().id(boxId).build())
//                );
//
//                theaterDtoList = extractDistinctAndFetch(
//                        boxDtoList,
//                        BoxDto::getTheaterId,
//                        theaterId -> getTheaterListByTheaterId(TheaterDtoBuilder.builder().id(theaterId).build())
//                );
//
////            }
////            case 0b101 -> {
//                // filmId, date만 선택됨
//            }
//            case 0b110 -> {
////                // filmId, theaterId만 선택됨
////                filmPlayingTableDtoList = getFilmPlayingTableListByFilmIdAndTheater(FilmPlayingTableDtoBuilder.builder()
////                        .filmId(selectFilmReservationDto.getFilmId())
////                        .
////                        .build());
//
//            }
//            case 0b111 -> {
//                // filmId, theaterId, date 모두 선택됨
//            }
//        }
//
//        map.put("filmPlayingTableDtoList", filmPlayingTableDtoList);
//
//        map.put("filmDtoList", filmDtoList);
//
//        map.put("boxTypeDtoList", boxTypeDtoList);
//        map.put("boxDtoList", boxDtoList);
//        map.put("theaterDtoList", theaterDtoList);
//
//        return Map.of();
//    }
//
//    private int makeFlag(SelectFilmReservationDto selectFilmReservationDto) {
//
//        int filmId = selectFilmReservationDto.getFilmId();
//        int theaterId = selectFilmReservationDto.getTheaterId();
//        LocalDate date = selectFilmReservationDto.getDate();
////        System.out.println("date = " + date);
//
//        // 3비트 조합: filmId(4) + theaterId(2) + date(1)
//        int flag = 0;
//        if (filmId != 0) flag |= 4;
//        if (theaterId != 0) flag |= 2;
//        if (date != null) flag |= 1;
//
//        return flag;
//    }
//
//
//    // 2차
////    private <E, T> List<? super T> func(List<E> paramList, T t) {
////
////        Set<Integer> uniqueSet = paramList.stream().map().collect(Collectors.toSet());
////
////        List<? super T> returnList = new ArrayList<>();
////        for (Integer id : uniqueSet) {
////            returnList =
////        }
////
////        return null;
////    }
//
//
//    // 3차
//    private <E, K, R> List<R> extractDistinctAndFetch(
//            List<E> inputList,
//            Function<E, K> keyExtractor,
//            Function<K, List<R>> fetchFunction
//    ) {
//        return inputList.stream()
//                .map(keyExtractor)
//                .filter(Objects::nonNull)
//                .distinct()
//                .flatMap(id -> fetchFunction.apply(id).stream())
//                .toList();
//    }
//
//
//    private List<FilmPlayingTableDto> getFilmPlayingTableListByDate(FilmPlayingTableDto filmPlayingTableDto) {
//        if (filmPlayingTableDto.getDate() == null) {
//            filmPlayingTableDto.setDate(LocalDate.now());
//            filmPlayingTableDto.setTime(LocalTime.now().withNano(0));
//        }
//        if (filmPlayingTableDto.getTime() == null) {
//            filmPlayingTableDto.setTime(LocalTime.of(0, 0, 0));
//        }
//        return mainPageSqlMapper.selectFilmPlayingTableListByDate(filmPlayingTableDto);
//    }
//    private List<FilmPlayingTableDto> getFilmPlayingTableListByBoxTypeId(FilmPlayingTableDto filmPlayingTableDto) {
//        return mainPageSqlMapper.selectFilmPlayingTableListByBoxTypeId(filmPlayingTableDto);
//    }
//    private List<FilmPlayingTableDto> getFilmPlayingTableListByFilmId(FilmPlayingTableDto filmPlayingTableDto) {
//        return mainPageSqlMapper.selectFilmPlayingTableListByFilmId(filmPlayingTableDto);
//    }
//    private List<FilmPlayingTableDto> getFilmPlayingTableListByFilmIdAndTheater(FilmPlayingTableDto filmPlayingTableDto) {
//        return null;
//    }
//
//
//    private List<FilmDto> getFilmListByFilmId(FilmDto filmDto) {
//        return mainPageSqlMapper.selectFilmListByFilmId(filmDto);
//    }
//
//
//    private List<BoxTypeDto> getBoxTypeListByBoxTypeId(BoxTypeDto boxTypeDto) {
//        return mainPageSqlMapper.selectBoxTypeListByBoxTypeId(boxTypeDto);
//    }
//    private List<BoxTypeDto> getBoxTypeListByBoxId(BoxTypeDto boxTypeDto) {
//        return mainPageSqlMapper.selectBoxTypeListByBoxId(boxTypeDto);
//    }
//
//
//
//    private List<BoxDto> getBoxListByBoxId(BoxDto boxDto) {
//        return mainPageSqlMapper.selectBoxListByBoxId(boxDto);
//    }
//    private List<BoxDto> getBoxListByTheaterId(BoxDto boxDto) {
//        return mainPageSqlMapper.selectBoxListByTheaterId(boxDto);
//    }
//
//
//
//    private List<TheaterDto> getAllTheater() {
//        return mainPageSqlMapper.selectAllTheater();
//    }
//    private List<TheaterDto> getTheaterListByTheaterId(TheaterDto theaterDto) {
//        return mainPageSqlMapper.selectTheaterListByTheaterId(theaterDto);
//    }
//
//
//
//}
//
//
//// 4차
////class ChainFetcher {
////
////    /**
////     * 입력 리스트에서 ID를 뽑아 중복 제거한 후 조회 → 다음 단계를 위해 리스트로 반환
////     */
////    public static <E, K, R> List<R> fetchDistinct(
////            List<E> inputList,
////            Function<E, K> keyExtractor,
////            Function<K, List<R>> fetchFunction
////    ) {
////        return inputList.stream()
////                .map(keyExtractor)
////                .filter(Objects::nonNull)
////                .distinct()
////                .flatMap(id -> fetchFunction.apply(id).stream())
////                .toList();
////    }
////
////    /**
////     * 이전 결과에서 이어서 다음 단계로 조회
////     */
////    public static <E, K, R> Function<List<E>, List<R>> chain(
////            Function<E, K> keyExtractor,
////            Function<K, List<R>> fetchFunction
////    ) {
////        return input -> fetchDistinct(input, keyExtractor, fetchFunction);
////    }
////}
//
//// 4차 사용
//    // 체이닝 방식
//        // 하지만 중간 결과물 저장 불가
////List<TheaterDto> theaterDtoList =
////        ChainFetcher.chain(FilmPlayingTableDto::getFilmId,
////                        filmId -> getFilmListByFilmId(FilmDtoBuilder.builder().id(filmId).build()))
////                .andThen(ChainFetcher.chain(FilmDto::getId,
////                        filmId -> getBoxTypeListByFilmId(filmId)))
////                .andThen(ChainFetcher.chain(BoxTypeDto::getBoxId,
////                        boxId -> getBoxListByBoxId(BoxDtoBuilder.builder().id(boxId).build())))
////                .andThen(ChainFetcher.chain(BoxDto::getTheaterId,
////                        theaterId -> getTheaterListByTheaterId(TheaterDtoBuilder.builder().id(theaterId).build())))
////                .apply(filmPlayingTableDtoList);
////
