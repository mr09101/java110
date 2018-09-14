package bitcamp.java110.cms.dao.impl;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import bitcamp.java110.cms.annotation.Component;
import bitcamp.java110.cms.dao.StudentDao;
import bitcamp.java110.cms.domain.Student;
//@Component
public class StudentFile2Dao implements StudentDao{

    private List<Student> list=new ArrayList<>();

    static String defaultFilename="data/student2.dat";
    String filename;
        
        public StudentFile2Dao(String filename) {
            this.filename=filename;
            File dataFile=new File(filename);
        
        try (
                FileInputStream in0=new FileInputStream(dataFile);
                BufferedInputStream in1=new BufferedInputStream(in0);
                ObjectInputStream in=new ObjectInputStream(in1);
                ){
            list=(List<Student>)in.readObject();
           /* while (true) {
                String line=in.readLine();
                if(line==null)
                    break;
                String[] values=line.split(",");

                Student s=new Student();
                s.setEmail(values[0]);
                s.setName(values[1]);
                s.setPassword(values[2]);
                s.setSchool(values[3]);
                s.setTel(values[4]);
                s.setWorking(Boolean.parseBoolean(values[5]));

                list.add(s);
            }*/

        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    public StudentFile2Dao() {
        this(defaultFilename);
    }
    private void save() {
        File dataFile = new File(filename);


        try (
                FileOutputStream out0=new FileOutputStream(dataFile);
                BufferedOutputStream out1=new BufferedOutputStream(out0);
                ObjectOutputStream out=new ObjectOutputStream(out1)
                ){
            out.writeObject(list);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }


    public int insert(Student student)throws MandatoryValueDaoException,
    DuplicationDaoException {
        if(student.getName().length()==0 || 
                student.getEmail().length()==0 ||
                student.getPassword().length()==0 
                ) {
            throw new MandatoryValueDaoException("필수 입력항목이 비었습니다.");
        }

        for (Student item: list) {
            if(item.getEmail().equals(student.getEmail())) {
                throw new MandatoryValueDaoException("필수 입력항목이 비었습니다.");
            }
        }
        list.add(student);
        save();
        return 1;
    }

    public List<Student> findAll() {
        return list;


    }
    public Student findByEmail(String email) {
        for (Student item: list) {
            if(item.getEmail().equals(email)) {
                return item;
            }
        }
        return null;

    }

    public int delete(String email) {
        for (Student item: list) {
            if(item.getEmail().equals(email)) {
                list.remove(email);
                return 1;
            }
        }
        save();
        return 0;
    }
}
