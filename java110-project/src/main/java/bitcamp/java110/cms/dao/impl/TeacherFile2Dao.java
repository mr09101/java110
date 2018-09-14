package bitcamp.java110.cms.dao.impl;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import bitcamp.java110.cms.annotation.Component;
import bitcamp.java110.cms.dao.TeacherDao;
import bitcamp.java110.cms.domain.Manager;
import bitcamp.java110.cms.domain.Teacher;
//@Component
public class TeacherFile2Dao implements TeacherDao{
    static String defaultFilename="data/teacher2.dat";
    String filename;
    private List<Teacher> list=new ArrayList<>();

    public TeacherFile2Dao(String filename) {
        this.filename=filename;
        File dataFile = new File(filename);
        try ( 
                FileInputStream in0=new FileInputStream(dataFile);
                BufferedInputStream in1=new BufferedInputStream(in0);
                ObjectInputStream in = new ObjectInputStream(in1);
                ){
            list=(List<Teacher>)in.readObject();
//            while (true) {
//                String line=in.readLine();
//                if(line==null)
//                    break;
//                String[] values=line.split(",");
//
//                Teacher s=new Teacher();
//                s.setEmail(values[0]);
//                s.setName(values[1]);
//                s.setPassword(values[2]);
//                s.setPay(Integer.parseInt(values[3]));
//                s.setTel(values[4]);
//                s.setSubjects(values[5]);
//
//                list.add(s);
//            }

        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    public TeacherFile2Dao() {
        this(defaultFilename);
        
    }
    private void save() {
        File dataFile = new File(filename);


        try (
                FileOutputStream out0=new FileOutputStream(dataFile);
                BufferedOutputStream out1 = new BufferedOutputStream(out0);
                ObjectOutputStream out=new ObjectOutputStream(out1);
                ){
            out.writeObject(list);
//            for(Teacher s:list) {
//                out.write(
//                        String.format("%s,%s,%s,%s,%s,%s\n",
//                                s.getEmail(),
//                                s.getName(),
//                                s.getPassword(),
//                                s.getPay(),
//                                s.getTel(),
//                                s.getSubjects()));
//            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }


    public int insert(Teacher teacher)throws MandatoryValueDaoException,
    DuplicationDaoException {
        if(teacher.getName().length()==0 || 
                teacher.getEmail().length()==0 ||
                teacher.getPassword().length()==0 
                ) {
            throw new MandatoryValueDaoException("필수 입력항목이 비었습니다.");
        }
        for (Teacher item: list) {
            if(item.getEmail().equals(teacher.getEmail())) {
                throw new DuplicationDaoException("같은 이메일이 이미 등록되었습니다.");
            }
        }
        list.add(teacher);
        save();
        return 1;
    }
        

    public List<Teacher> findAll() {
        return list;


    }
    public Teacher findByEmail(String email) {
        for (Teacher item: list) {
            if(item.getEmail().equals(email)) {
                return item;
            }
        }
        return null;

    }

    public int delete(String email) {
        for (Teacher item: list) {
            if(item.getEmail().equals(email)) {
                list.remove(email);
                return 1;
            }
        }
        save();
        return 0;
    }
}
