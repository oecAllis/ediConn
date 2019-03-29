package com.oecgroup.conn;

import java.io.File;
import java.io.IOException;
import java.util.List;

import net.schmizz.sshj.SSHClient;
import net.schmizz.sshj.sftp.RemoteResourceInfo;
import net.schmizz.sshj.sftp.SFTPClient;
import net.schmizz.sshj.xfer.FileSystemFile;

/**
 * Created by Allis Kuo on 2019-03-19
 */
public class SFTPDownload {

  public static void main(String[] args)
      throws IOException {
    final String host = "52.199.51.30";
    final Integer port = 2222;
    final String userName = "oecdev";
    final String password = "OecDev^345";
    final String localPath = "D://tmp/test/BEALLS/";
    final String sftpGetPath = "/Testing/BEALLS/";

    final SSHClient ssh = new SSHClient();
    ssh.loadKnownHosts();

    try {
      ssh.connect(host,port);
      ssh.authPassword(userName,password);

      if(ssh.isAuthenticated()){
        final SFTPClient sftp = ssh.newSFTPClient();
        try {
//        long size = sftp.size("/Testing/AAP/APZU4583796_5_20180724163534.xml");
//        System.out.println("Size:" + size);
          List<RemoteResourceInfo> ls = sftp.ls(sftpGetPath);
          for(RemoteResourceInfo info : ls) {
            File dir = new File(localPath);
            if(!dir.exists()){
              dir.mkdirs();
            }
            File file = new File(localPath + info.getName());
            sftp.get(info.getPath(), new FileSystemFile(file));
            System.out.println("Get Path：" + info.getPath());
            System.out.println("Download To:" + file.getAbsolutePath());
          }
        }
        finally {
          sftp.close();
        }
      }
      else {
        System.out.println("connect fail");
      }
    }
    finally {
      ssh.disconnect();
    }
  }
}