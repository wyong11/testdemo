package com.sy.giteaapi;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.eclipse.jgit.api.CloneCommand;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.internal.storage.file.FileRepository;
import org.eclipse.jgit.lib.Ref;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;

public class JgitTest {
	public static String remotePath = "http://localhost:3000/teacher/testgit.git";//远程库路径
	public static String localPath = "D:\\git\\";//下载已有仓库到本地路径
	
	/**
	  * 仓库初始化
	  */
	public static void gitInit() throws IOException{
	        Repository newlyCreatedRepo = FileRepositoryBuilder.create(new File(localPath+"/.git"));
	        if(newlyCreatedRepo!=null){
	            newlyCreatedRepo.create();
	        }
	        System.err.println("git init success");
	}
	/**
	 * 克隆远程仓库
	 * @param remotePath:"url"
	 * @param branch：master
	 * @param userName："userName"
	 * @param passWord："password"
	 * @throws GitAPIException
	 */
	public static void gitClone(String remotePath,String branch,String userName,String passWord) throws GitAPIException {
	    //设置远程服务器上的用户名和密码
	    UsernamePasswordCredentialsProvider usernamePasswordCredentialsProvider =new
	            UsernamePasswordCredentialsProvider(userName,passWord);
	 
	    File file = new File(localPath);
        if(file.exists()){
        	filesDelete(file.listFiles());
        }
	    //克隆代码库命令
	    CloneCommand cloneCommand = Git.cloneRepository();
	    
	    Git git= cloneCommand.setURI(remotePath) //设置远程URI
	            .setBranch(branch) //设置clone下来的分支
	            .setDirectory(new File(localPath)) //设置下载存放路径
	            .setCredentialsProvider(usernamePasswordCredentialsProvider) //设置权限验证
	            .call();
	    System.err.println("git clone success");
	}
	/**
	 * 添加到缓存区：对于删除的文件jgit添加失败
	 * @throws IOException
	 * @throws GitAPIException
	 */
	public static void gitAdd() throws IOException, GitAPIException {
	    Git git = new Git(new FileRepository(localPath + "/.git"));
	    git.add().addFilepattern(".").call();
	    System.err.println("git add success");
	}
	/**
	 * 本地代码提交
	 * @param msg:提交信息
	 * @throws IOException
	 * @throws GitAPIException
	 */
	public static void gitCommit(String msg) throws IOException, GitAPIException {
	    Git git = new Git(new FileRepository(localPath + "/.git"));
	    //全部提交
	    git.commit().setAll(true).setMessage(msg).call();
	    System.err.println("git commit success");
	}
	/**
	 * push本地代码到远程仓库
	 * @param branch：master
	 * @param userName："userName"
	 * @param passWord："password"
	 * @throws IOException
	 * @throws GitAPIException
	 */
	public static void gitPush(String branch,String userName,String passWord) throws IOException, GitAPIException {
	    UsernamePasswordCredentialsProvider usernamePasswordCredentialsProvider =new
	               UsernamePasswordCredentialsProvider(userName,passWord);
	    //git仓库地址
	    Git git = new Git(new FileRepository(localPath+"/.git"));
	 
	    //检查新建的分支是否已经存在，如果存在则将已存在的分支强制删除并新建一个分支
	    List<Ref> refs = git.branchList().call();
	    for (Ref ref : refs) {
	    	System.out.println(ref.getName());
	        if (ref.getName().equals("refs/heads/"+branch)) {
	            System.out.println("Removing branch before");
	            git.branchDelete().setBranchNames(branch).setForce(true)
	                        .call();
	            break;
	        }
	    }
	    //新建分支
	    Ref ref = git.branchCreate().setName(branch).call();
	    //推送到远程
	     git.push().add(ref).setCredentialsProvider(usernamePasswordCredentialsProvider).call();
	 
	    /*git.push().setRemote("origin/"+branch)
	                .setCredentialsProvider(usernamePasswordCredentialsProvider).call();*/
	    System.err.println("git push success");
	}
	
	/**
	 * 递归删除本地服务器文件
	 * @param files
	 */
	public static void filesDelete(File[] files){
	    if(files!=null&&files.length>0){
	        for (File file:files) {
	            //递归删除文件
	            File[] childFiles = file.listFiles();
	            filesDelete(childFiles);
	            file.delete();
	            System.err.println(file.getName()+" delete success");
	        }
	    }
	}
	
	public static void main(String[] args){
	    String remotePath = "http://localhost:3000/teacher/testgit.git";
	    String branch = "master";
	    String userName = "teacher";
	    String passWord = "teacher";
	 
	    String msg = "";
	    
		try {
			filesDelete(new File(localPath).listFiles());
			//gitInit();
			gitClone(remotePath,branch,userName,passWord);
			//添加要提交的新文件
			//gitAdd();
			//gitCommit(msg);
			//filesDelete(new File(localPath).listFiles());
			//gitPush("jgittest",userName,passWord);
		} catch (GitAPIException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}

