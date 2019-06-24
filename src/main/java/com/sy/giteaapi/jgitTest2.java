/*
 * package com.sy.giteaapi;
 * 
 * import java.io.File; import java.io.IOException; import java.util.List;
 * 
 * import org.eclipse.jgit.api.Git; import
 * org.eclipse.jgit.api.errors.GitAPIException; import org.eclipse.jgit.lib.Ref;
 * 
 * public class jgitTest2 { public static String localRepoPath = "D:/repo3";
 * public static String localRepoGitConfig = "D:/repo3/.git"; public static
 * String remoteRepoURI = "http://localhost:3000/16201101/16201101.git"; public
 * static String localCodeDir = "D:/platplat";
 * 
 *//**
	 * 新建一个分支并同步到远程仓库
	 * 
	 * @param branchName
	 * @throws IOException
	 * @throws GitAPIException
	 *//*
		 * public static String newBranch(String branchName){ String newBranchIndex =
		 * "refs/heads/"+branchName; String gitPathURI = ""; Git git = null; try {
		 * 
		 * //检查新建的分支是否已经存在，如果存在则将已存在的分支强制删除并新建一个分支 List<Ref> refs =
		 * git.branchList().call(); for (Ref ref : refs) { if
		 * (ref.getName().equals(newBranchIndex)) {
		 * System.out.println("Removing branch before");
		 * git.branchDelete().setBranchNames(branchName).setForce(true) .call(); break;
		 * } } //新建分支 Ref ref = git.branchCreate().setName(branchName).call(); //推送到远程
		 * git.push().add(ref).call(); gitPathURI = remoteRepoURI + " " + "feature/" +
		 * branchName; } catch (GitAPIException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); }
		 * 
		 * return gitPathURI; }
		 * 
		 * public static void commitFiles() throws IOException, GitAPIException{ String
		 * filePath =
		 * "C:/Users/wyong/Documents/Tencent Files/2538934830/FileRecv/Tuoli002/index.php"
		 * ; Git git = Git.open( new File(localRepoGitConfig) ); //创建用户文件的过程 File myfile
		 * = new File(filePath); myfile.createNewFile();
		 * git.add().addFilepattern("pets").call(); //提交
		 * git.commit().setMessage("Added pets").call(); //推送到远程 git.push().call(); }
		 * 
		 * public static boolean pullBranchToLocal(String cloneURL){ boolean resultFlag
		 * = false; String[] splitURL = cloneURL.split(" "); String branchName =
		 * splitURL[1]; String fileDir = localCodeDir+"/"+branchName; //检查目标文件夹是否存在 File
		 * file = new File(fileDir); if(file.exists()){ deleteFolder(file); } Git git;
		 * try { git = Git.open( new File(localRepoGitConfig) );
		 * git.cloneRepository().setURI(cloneURL).setDirectory(file).call(); resultFlag
		 * = true; } catch (IOException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); } catch (GitAPIException e) { // TODO Auto-generated
		 * catch block e.printStackTrace(); } return resultFlag; }
		 * 
		 * public static void deleteFolder(File file){ if(file.isFile() ||
		 * file.list().length==0){ file.delete(); }else{ File[] files =
		 * file.listFiles(); for(int i=0;i<files.length;i++){ deleteFolder(files[i]);
		 * files[i].delete(); } } }
		 * 
		 * public static void setupRepo() throws GitAPIException{ //建立与远程仓库的联系，仅需要执行一次
		 * Git git = Git.cloneRepository().setURI(remoteRepoURI).setDirectory(new
		 * File(localRepoPath)).call(); }
		 * 
		 * public static void main(String[] args) { try { setupRepo();
		 * pullBranchToLocal("http://localhost:3000/16201101/16201101.git"); try {
		 * commitFiles(); } catch (IOException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); } } catch (GitAPIException e) { // TODO Auto-generated
		 * catch block e.printStackTrace(); }
		 * 
		 * 
		 * }
		 * 
		 * }
		 */


package com.sy.giteaapi;

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
 * JGit API测试
 */
public class jgitTest2 {

    public String remotePath = "http://localhost:3000/16201101/16201101.git";//远程库路径
    public static String localPath = "D:\\project\\";//下载已有仓库到本地路径
    public String initPath = "D:\\test\\";//本地路径新建


    /**
     * 克隆远程库
     * @throws IOException
     * @throws GitAPIException
     */
   
    public void testClone() throws IOException, GitAPIException {
        //设置远程服务器上的用户名和密码
        UsernamePasswordCredentialsProvider usernamePasswordCredentialsProvider =new
                UsernamePasswordCredentialsProvider("16201101","123456");

        //克隆代码库命令
        CloneCommand cloneCommand = Git.cloneRepository();

       Git git= cloneCommand.setURI(remotePath) //设置远程URI
                .setBranch("master") //设置clone下来的分支
                .setDirectory(new File(localPath)) //设置下载存放路径
                .setCredentialsProvider(usernamePasswordCredentialsProvider) //设置权限验证
                .call();

        System.out.print(git.tag());
    }

    /**
    * 本地新建仓库
    */
   
    public void testCreate() throws IOException {
        //本地新建仓库地址
        Repository newRepo = FileRepositoryBuilder.create(new File(initPath + "/.git"));
        newRepo.create();
    }

    /**
    * 本地仓库新增文件
    */
   
    public void testAdd(String filepath) throws IOException, GitAPIException {
        File myfile = new File(filepath);
        
        myfile.createNewFile();
        //git仓库地址
        Git git = new Git(new FileRepository(localPath+"/.git"));

        //添加文件
        git.add().addFilepattern("myfile").call();
    }

    /**
    * 本地提交代码
    */
    public static void commitFiles(String filePath) throws IOException, GitAPIException{
       // String filePath = "";
        Git git = Git.open(new File(localPath+"/.git"));
        //创建用户文件的过程
        File myfile = new File(filePath);
        myfile.createNewFile();
        git.add().addFilepattern("pets").call();   
        //提交
        git.commit().setMessage("Added pets").call();   
        //推送到远程
        git.push().call();
    }
    
    public void testCommit(String filepath) throws IOException, GitAPIException,
            JGitInternalException {
        //git仓库地址
        Git git = new Git(new FileRepository(localPath+"/.git"));
        
        File myfile = new File(filepath);
        
        myfile.createNewFile();
        git.add().addFilepattern(filepath).call(); 
        //提交代码
        git.commit().setMessage("commit java file").call();
        
    }


    /**
    * 拉取远程仓库内容到本地
    */
    
    public void testPull() throws IOException, GitAPIException {

        UsernamePasswordCredentialsProvider usernamePasswordCredentialsProvider =new
                UsernamePasswordCredentialsProvider("16201101","123456");
        //git仓库地址
        Git git = new Git(new FileRepository(localPath+"/.git"));
        git.pull().setRemoteBranchName("master").
                setCredentialsProvider(usernamePasswordCredentialsProvider).call();
    }

    /**
    * push本地代码到远程仓库地址
    */
   
    public void testPush() throws IOException, JGitInternalException,
            GitAPIException {

        UsernamePasswordCredentialsProvider usernamePasswordCredentialsProvider =new
                UsernamePasswordCredentialsProvider("16201101","123456");
        //git仓库地址
        Git git = new Git(new FileRepository(localPath+"/.git"));   
        git.push().setRemote("origin").     setCredentialsProvider(usernamePasswordCredentialsProvider).call();
    }
    
    public static void main(String[] args){
    	String filepath = "C:/Users/wyong/Documents/Tencent Files/2538934830/FileRecv/Student/src/student/controller/LoginController.java";
    	jgitTest2 jt2 = new jgitTest2();
		/*
		 * String branch = "master"; String userName = "16201101"; String passWord =
		 * "123456";
		 * 
		 * String msg = "";
		 */
	    
	    try {
			jt2.testClone();
			//jt2.testAdd(filepath);
			jt2.testCommit(filepath);
			//jt2.commitFiles(filepath);
			jt2.testPush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (GitAPIException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

