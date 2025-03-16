package com.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.dao.FileInfoDao;
import com.example.entity.FileInfo;
import com.example.service.FileInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("FileInfoService")
public class FileInfoServiceImpl extends ServiceImpl<FileInfoDao, FileInfo> implements FileInfoService {

    @Resource
    private FileInfoDao FileInfoDao;

    public FileInfo add(FileInfo FileInfo) {
        FileInfoDao.insert(FileInfo);
        return FileInfo;
    }

    public void delete(Long id) {
        FileInfoDao.deleteById(id);
    }

    public void update(FileInfo FileInfo) {
        FileInfoDao.updateById(FileInfo);
    }

    public FileInfo findById(Long id) {
        return FileInfoDao.selectById(id);
    }
}
