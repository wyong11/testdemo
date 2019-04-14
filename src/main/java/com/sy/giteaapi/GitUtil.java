package com.sy.giteaapi;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.Status;
import org.eclipse.jgit.api.*;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.JGitInternalException;
import org.eclipse.jgit.internal.storage.file.FileRepository;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;



import java.io.File;
import java.io.IOException;

/**
 * 类描述：git工具类
 * @author lj 2017年10月27日 15:34
 */
public class GitUtil {
    //克隆仓库
    public String cloneRepository(String url, String localPath) {
        try {
            System.out.println("开始下载......");

            Git git = Git.cloneRepository()
                    .setURI(url)
                    .setDirectory(new File(localPath))
                    .setCloneAllBranches(true)
                    .call();

            System.out.println("下载完成......");

            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }
    //切换分支
    public void checkoutBranch(String localPath, String branchName){
        String projectURL = localPath + "\\.git";

        Git git = null;
        try {
            git = Git.open(new File(projectURL));
            git.checkout().setCreateBranch(true).setName(branchName).call();
            git.pull().call();
            System.out.println("切换分支成功");
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("切换分支失败");
        } finally{
            if (git != null) {
                git.close();
            }
        }   
    }

    //提交代码
    public void commit(String localPath,String pushMessage)  {
        String projectURL = localPath + "\\.git";
        Git git = null;
        try {
            git = Git.open(new File(projectURL));
            git.pull().call();
            Status status = git.status().call();
            if (status.hasUncommittedChanges() == false) {
                System.out.println("无已修改文件");
                return;
            }
            //忽略GitUtil.java文件
//            git.add().addFilepattern("GitUtil.java").call();
            git.add().addFilepattern(".").call();
            git.commit().setMessage(pushMessage).call();
            git.pull().call();
            git.push().call();

//            //查看log信息
//            for(RevCommit revCommit : git.log().call()){
//                System.out.println(revCommit);
//                System.out.println(revCommit.getFullMessage());
//                System.out.println(revCommit.getCommitterIdent().getName() + " " + revCommit.getCommitterIdent().getEmailAddress());
//            }
            System.out.println("提交成功");
        } catch (Exception e){
            e.printStackTrace();
            System.out.println("提交失败");
        } finally{
            if (git != null) {
                git.close();
            }
        }
    }
    
    public void testPull(String localPath) throws IOException, GitAPIException {

        UsernamePasswordCredentialsProvider usernamePasswordCredentialsProvider =new
                UsernamePasswordCredentialsProvider("username","password");
        //git仓库地址
        Git git = new Git(new FileRepository(localPath+"/.git"));
        git.pull().setRemoteBranchName("master").
                setCredentialsProvider(usernamePasswordCredentialsProvider).call();
    }
    
    public static void main(String[] args) {
        GitUtil gitUtil = new GitUtil();
        //git远程url地址
        String url = "https://gitee.com/xiaoqing11/ssm.git";
        String localPath = "C:/Users/wyong/Desktop/copy";
      //  String localPath = System.getProperty("user.dir");
       // String branchName = "15201126_branch";
        try {
            gitUtil.cloneRepository(url,localPath);
//            gitUtil.checkoutBranch(localPath,branchName);
           // gitUtil.commit(localPath,"测试提交1");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
