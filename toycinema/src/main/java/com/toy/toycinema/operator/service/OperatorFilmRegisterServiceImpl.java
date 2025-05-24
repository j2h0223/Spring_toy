package com.toy.toycinema.operator.service;

import com.toy.toycinema.dto.*;

import com.toy.toycinema.film.mapper.FilmSqlMapper;
import com.toy.toycinema.operator.mapper.OperatorSqlMapper;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class OperatorFilmRegisterServiceImpl implements OperatorFilmRegisterService {
    private final OperatorSqlMapper operatorSqlMapper;
    private final FilmSqlMapper filmSqlMapper;

    @Value("${file.upload.path}")
    private String uploadRootPath;

    public OperatorFilmRegisterServiceImpl(OperatorSqlMapper operatorSqlMapper, FilmSqlMapper filmSqlMapper) {
        this.operatorSqlMapper = operatorSqlMapper;
        this.filmSqlMapper = filmSqlMapper;
    }

    @Override
    public void registerFilmBasicInfo(RegisterFilmBasicInfoDto registerFilmBasicInfoDto) {
        operatorSqlMapper.insertFilmBasicInfo(registerFilmBasicInfoDto);
    }

    @Override
    public void registerFilmGenre(RegisterFilmAdditionalInfoDto registerFilmAdditionalInfoDto) {
        findGenreAndMakeRelation(registerFilmAdditionalInfoDto.getGenreDtoList(), registerFilmAdditionalInfoDto.getFilmId());
    }

    @Override
    public void registerFilmUrlYoutube(RegisterFilmAdditionalInfoDto registerFilmAdditionalInfoDto) {
        for (String url : registerFilmAdditionalInfoDto.getUrlYoutubeList()) {
            operatorSqlMapper.insertFilmUrlYoutube(url, registerFilmAdditionalInfoDto.getFilmId());
        }
    }

    @Override
    public Map<String, List<PeopleDto>> registerFilmDirectorAndActor(RegisterFilmAdditionalInfoDto registerFilmAdditionalInfoDto) {
        Map<String, List<PeopleDto>> peopleRegistrationRequiredMap = new HashMap<>();

        List<PeopleDto> directorList = findPeopleAndMakeRelationOrReturn(registerFilmAdditionalInfoDto.getDirectorDtoList(), registerFilmAdditionalInfoDto.getFilmId(), true);
        List<PeopleDto> actorList = findPeopleAndMakeRelationOrReturn(registerFilmAdditionalInfoDto.getActorDtoList(), registerFilmAdditionalInfoDto.getFilmId(), false);

        peopleRegistrationRequiredMap.put("peopleRationRequiredForDirector", directorList);
        peopleRegistrationRequiredMap.put("peopleRegistrationRequiredForActor", actorList);
//
        return peopleRegistrationRequiredMap;
    }

    @Override
    public void registerFilmPeople(RegisterFilmAdditionalInfoDto registerFilmAdditionalInfoDto) {
        insertPeople(registerFilmAdditionalInfoDto.getDirectorDtoList(), registerFilmAdditionalInfoDto.getFilmId(),  true);
        insertPeople(registerFilmAdditionalInfoDto.getActorDtoList(), registerFilmAdditionalInfoDto.getFilmId(),false);
    }

    @Override
    public void registerFilmType(RegisterFilmAdditionalInfoDto registerFilmAdditionalInfoDto) {
        findTypeAndMakeRelation(registerFilmAdditionalInfoDto.getTypeDtoList(), registerFilmAdditionalInfoDto.getFilmId());
    }

    @Override
    public void registerFilmPoster(RegisterFilmAdditionalInfoDto registerFilmAdditionalInfoDto, List<MultipartFile> uploadFiles) {
        loopForEachPoster(uploadFiles, registerFilmAdditionalInfoDto);
        operatorSqlMapper.updateFilmUrlMain(registerFilmAdditionalInfoDto.getUrlMain(), registerFilmAdditionalInfoDto.getFilmId());
        for (FilmPosterDto filmPosterDto : registerFilmAdditionalInfoDto.getFilmPosterDtoList()) {
            operatorSqlMapper.insertFilmPoster(filmPosterDto);
        }
    }

    private void loopForEachPoster(List<MultipartFile> uploadFiles, RegisterFilmAdditionalInfoDto registerFilmAdditionalInfoDto) {
        if (uploadFiles != null) {
            for (MultipartFile multipartFile : uploadFiles) {
                if (multipartFile.isEmpty()) {
                    continue;
                }

                FilmPosterDto filmPosterDto = null;
                try {
                    System.out.println("Arrays.toString(multipartFile.getBytes()) = " + Arrays.toString(multipartFile.getBytes()));
                    filmPosterDto = makePathForPoster(multipartFile.getBytes(), multipartFile.getOriginalFilename(), registerFilmAdditionalInfoDto);
                } catch (IOException e) {
//                    throw new RuntimeException(e);
                    e.printStackTrace();
                }
            }
        }
    }

    private FilmPosterDto makePathForPoster(byte[] buffer, String originalFilename, RegisterFilmAdditionalInfoDto registerFilmAdditionalInfoDto) {


        String fileDirPathString = uploadRootPath + registerFilmAdditionalInfoDto.getFilmOriginalName() + "/";
        registerFilmAdditionalInfoDto.setUrlMain(fileDirPathString);
        System.out.println("fileDirPathString = " + fileDirPathString);
        
        File fileDirPath = new File(fileDirPathString);
        if (!fileDirPath.exists()) {
            fileDirPath.mkdirs();
        }

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy_MM_dd");
        String fileNameByDate = simpleDateFormat.format(new Date());

        String uuid = UUID.randomUUID().toString();
        long currentTime = System.currentTimeMillis();
        String fileNameByUUIDAndTime = uuid + "_" + currentTime;

        String fileName = fileNameByDate + "_" + fileNameByUUIDAndTime;
        String ext = originalFilename.substring(originalFilename.lastIndexOf("."));
        fileName += ext;
        System.out.println("fileName = " + fileName);

        ByteArrayInputStream inputStream = new ByteArrayInputStream(buffer);
        System.out.println("inputStream = " + inputStream);
        try {
            Thumbnails.of(inputStream).scale(1.0)
                    .toFile(fileDirPathString + fileName);
        } catch (IOException e) {
//            throw new RuntimeException(e);
            e.printStackTrace();
        }

        FilmPosterDto filmPosterDto = new FilmPosterDto();
        filmPosterDto.setFilmId(registerFilmAdditionalInfoDto.getFilmId());
        filmPosterDto.setOriginalName(originalFilename);
        filmPosterDto.setUrlSub(fileName);
        registerFilmAdditionalInfoDto.getFilmPosterDtoList().add(filmPosterDto);

        return null;
    }


    private void findTypeAndMakeRelation(List<TypeDto> typeDtoList, int filmId) {
        for (TypeDto typeDto : typeDtoList) {
            TypeDto searchedType = operatorSqlMapper.selectTypeDtoByTypeName(typeDto);
            if (searchedType == null) {
                operatorSqlMapper.insertType(typeDto);
                searchedType = operatorSqlMapper.selectTypeDtoByTypeName(typeDto);
            }
            FilmTypeDto filmTypeDto = new FilmTypeDto();
            filmTypeDto.setTypeId(searchedType.getId());
            filmTypeDto.setFilmId(filmId);
            operatorSqlMapper.insertFilmType(filmTypeDto);
        }
    }

    private void insertPeople(List<PeopleDto> peopleDtoList, int filmId, boolean isDirector) {
        for (PeopleDto peopleDto : peopleDtoList) {
            operatorSqlMapper.insertPeople(peopleDto);
            System.out.println("peopleDto = " + peopleDto);

            FilmPeopleDto filmPeopleDto = new FilmPeopleDto();
            filmPeopleDto.setPeopleId(peopleDto.getId());
            filmPeopleDto.setFilmId(filmId);

            if (isDirector) {
                operatorSqlMapper.insertFilmDirectorPeople(filmPeopleDto);
            } else {
                operatorSqlMapper.insertFilmActorPeople(filmPeopleDto);
            }

        }
    }



    private void findGenreAndMakeRelation(List<GenreDto> genreDtoList, int filmId) {
        for (GenreDto genreDto : genreDtoList) {
            GenreDto searchedGenre = operatorSqlMapper.selectGenreDto(genreDto);
            if (searchedGenre == null) {
                operatorSqlMapper.insertGenre(genreDto);
                searchedGenre = operatorSqlMapper.selectGenreDto(genreDto);
            }
            FilmGenreDto filmGenreDto = new FilmGenreDto();
            filmGenreDto.setGenreId(searchedGenre.getId());
            filmGenreDto.setFilmId(filmId);
            operatorSqlMapper.insertFilmGenre(filmGenreDto);
        }
    }


    private List<PeopleDto> findPeopleAndMakeRelationOrReturn(List<PeopleDto> peopleDtoList, int filmId, boolean isDirector) {

        List<PeopleDto> registrationRequired = new ArrayList<>();

        for (PeopleDto peopleDto : peopleDtoList) {
            PeopleDto searchedPeople = operatorSqlMapper.selectPeopleDto(peopleDto);
            if (searchedPeople == null) {
                registrationRequired.add(peopleDto);
            } else if (isDirector) {
                FilmPeopleDto filmPeopleDto = new FilmPeopleDto();
                filmPeopleDto.setPeopleId(peopleDto.getId());
                filmPeopleDto.setFilmId(filmId);
                operatorSqlMapper.insertFilmDirectorPeople(filmPeopleDto);
            } else {
                FilmPeopleDto filmPeopleDto = new FilmPeopleDto();
                filmPeopleDto.setPeopleId(peopleDto.getId());
                filmPeopleDto.setFilmId(filmId);
                operatorSqlMapper.insertFilmActorPeople(filmPeopleDto);
            }
        }

        return registrationRequired;
    }


}
