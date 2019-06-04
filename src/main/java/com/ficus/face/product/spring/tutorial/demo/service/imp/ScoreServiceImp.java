package com.ficus.face.product.spring.tutorial.demo.service.imp;

import com.ficus.face.product.spring.tutorial.demo.dao.ScoreDAO;
import com.ficus.face.product.spring.tutorial.demo.dao.StudentDAO;
import com.ficus.face.product.spring.tutorial.demo.dao.SubjectDAO;
import com.ficus.face.product.spring.tutorial.demo.dto.StudentScoreDTO;
import com.ficus.face.product.spring.tutorial.demo.dto.StudentScoreResult;
import com.ficus.face.product.spring.tutorial.demo.dto.SubjectScoreDTO;
import com.ficus.face.product.spring.tutorial.demo.persistance.Score;
import com.ficus.face.product.spring.tutorial.demo.persistance.Student;
import com.ficus.face.product.spring.tutorial.demo.persistance.Subject;
import com.ficus.face.product.spring.tutorial.demo.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * implementation for services interface provide fot score
 */
@Service
public class ScoreServiceImp implements ScoreService {

    /**
     * hashmap for subject
     * key:subject id
     * value:subjecy name
     */
    private Map<String, String> subjectIdMap = new HashMap<>();

    /**
     * hashmap for student id
     * key:student id
     * value:student name
     */
    private Map<String, String> studentIdMap = new HashMap<>();

    @Autowired
    ScoreDAO scoreDao;

    @Autowired
    StudentDAO studentDao;

    @Autowired
    SubjectDAO subjectDao;

    //add score
    @Override
    public boolean addScore(Score score) {

        if (studentDao.findById(score.getStudentId()) == null) return false;

        if (subjectDao.findById(score.getSubjectId()) == null) return false;

        scoreDao.save(score);
        return true;
    }

    //batch add scores
    @Override
    public void batchAddScores(List<Score> scores) {
        if (CollectionUtils.isEmpty(scores)) return;

        scoreDao.batchInsert(scores);
    }

    //get all scores of a student by student id
    @Override
    public List<SubjectScoreDTO> getScoreByStudentId(String id) {
        //scores where student_id=id
        List<Score> scores = scoreDao.findByStudentId(id);
        if (CollectionUtils.isEmpty(scores)) return null;

        List<SubjectScoreDTO> result = new ArrayList<>();
        for (Score score : scores) {
            String subjectId = score.getSubjectId();

            if (!subjectIdMap.containsKey(subjectId)) {
                Subject subject = subjectDao.findById(subjectId);

                //not found in subject collection
                if (subject == null) continue;

                //update the subject hashmap
                subjectIdMap.put(subjectId, subject.getName());
            }

            String subjectName = subjectIdMap.get(subjectId);
            result.add(new SubjectScoreDTO(subjectName, score.getScore()));
        }

        //sort the scores by subject name
        result.sort(new Comparator<SubjectScoreDTO>() {
            @Override
            public int compare(SubjectScoreDTO o1, SubjectScoreDTO o2) {
                return o1.getSubjectName().compareTo(o2.getSubjectName());
            }
        });

        return result;
    }

    //get scores of all students in a subject by subject id
    @Override
    public StudentScoreResult getScoreBySubjectId(String id) {
        List<Score> scores = scoreDao.findBySubjectId(id);
        if (CollectionUtils.isEmpty(scores)) return null;

        int num = 0;  //count for valid scores
        float scoreSum = 0;  //sum for scores
        float max = Float.MIN_VALUE;  //max score
        float min = Float.MAX_VALUE;  //min score

        List<StudentScoreDTO> studentScores = new ArrayList<>();
        for (Score score : scores) {
            String studentId = score.getStudentId();

            if (!studentIdMap.containsKey(studentId)) {
                Student student = studentDao.findById(studentId);

                //not found in Student collection
                if (student == null) continue;

                //update student hashmap
                studentIdMap.put(studentId, student.getName());
            }

            String studentName = studentIdMap.get(studentId);

            scoreSum += score.getScore();
            if (score.getScore() > max) max = score.getScore();
            if (score.getScore() < min) min = score.getScore();

            //add to list
            studentScores.add(new StudentScoreDTO(studentName, score.getScore()));

            num++;
        }

        //sort the scores of a student from high to low
        studentScores.sort(new Comparator<StudentScoreDTO>() {
            @Override
            public int compare(StudentScoreDTO o1, StudentScoreDTO o2) {
                return o1.getScore() > o2.getScore() ? 1 : -1;
            }
        });

        float avg = 0;
        if (num > 0) {
            avg = scoreSum / num;
        }

        //set result
        StudentScoreResult result = new StudentScoreResult();
        result.setAvg(avg);
        result.setMax(max);
        result.setMin(min);
        result.setStudentScores(studentScores);

        return result;

    }
}
