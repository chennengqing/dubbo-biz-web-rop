package org.cnq.test.task;

import java.io.Serializable;

import org.springframework.stereotype.Component;

/*@Component("fileUrlMonitorTask")
public class FileUrlMonitorTask implements Serializable {

	private static Logger logger = Logger.getLogger(FileUrlMonitorTask.class);
	@Resource
	private VideoWebService videoWebService;

	private static Integer IS_WATERMARK = 1;
	
	@Scheduled(cron = "0 0/1 * * * ?")  
	public void run() {
		

		QiniuMediaCommon qiniu=new QiniuMediaCommon();
		// 获取已提交转码任务成功的所有任务
		List<Video> list = videoWebService.findListVideoByJobid();
		for (Video videoResource : list) {
			String mp4=videoResource.getVideoUrl().substring(0,videoResource.getVideoUrl().lastIndexOf("."))+".mp4";
			boolean bb=	qiniu.getFileInfo(QiniuMediaCommon.MEDIA_BUCKET,mp4);
			if(bb){
				videoResource.setVideoUrl(QiniuMediaCommon.MEDIA_DOMAIN_NAME+mp4);
				videoResource.setState(EnumConstant.VideoStatus.ENABLE.getValue().byteValue());
				try {
					videoWebService.updateVideo(videoResource);
				} catch (AppleException e) {
					e.printStackTrace();
				}
				
				int status=	qiniu.prefetch(videoResource.getVideoUrl());
				System.out.println("预取视频状态:"+status);
			}
			
		}
		
		List<Video> listnoturl = videoWebService.findListVideo();
		for (Video videoResource : listnoturl) {
			try {
				// 判断文件是否上传到百度云
				if (qiniu.getFileInfo(QiniuMediaCommon.SOURCE_BUCKET,videoResource.getVideoUrl())) {
					
					String watermarkImage = "http://video.9zhiad.com/logo_03.png";
					QiniuUtil q=new QiniuUtil();
					q.setFormat("mp4");
					q.setResolution("640x360");
				
			
					if(videoResource.getIsWatermark().toString().equals("1")){ //1代表需要加水印的视频
						q.setEncodedRemoteImageUrl(watermarkImage);
						q.setGravity("NorthEast");
					}
					
					videoResource.setJobid("22");
					videoWebService.updateVideo(videoResource);

					qiniu.convert(q,videoResource.getVideoUrl());
					
					
				} else {
					// 在百度云文件不存在
					videoResource.setState(EnumConstant.VideoStatus.FAILED.getValue().byteValue());
					videoWebService.updateVideo(videoResource);
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		
		
		// 获取已提交转码任务成功的所有任务
		List<Video> list = videoWebService.findListVideoByJobid();
		for (Video videoResource : list) {
			try {
				// 获取指定任务的详细信息
				GetJobResponse job = BaiduMediaTranscodeCommon.getJob(videoResource.getJobid());
				String status = job.getJobStatus();
				if (status.equals("SUCCESS")) {
					// 拼装视频下载地址并更改数据库
					String url = BaiduMediaTranscodeCommon.getMp4(videoResource.getVideoUrl());
					videoResource.setVideoUrl(url);
					videoResource.setState(EnumConstant.VideoStatus.ENABLE.getValue().byteValue());
					videoWebService.updateVideo(videoResource);
				}else if(status.equals("FAILED")){
					videoResource.setState(EnumConstant.VideoStatus.TRANSCODE_FAILED.getValue().byteValue());
					videoWebService.updateVideo(videoResource);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		// 查询未提交转转码任务的视频
		List<Video> listnoturl = videoWebService.findListVideo();
		for (Video videoResource : listnoturl) {
			try {
				// 判断文件是否上传到百度云
				if (BaiduMediaTranscodeCommon.getObject(videoResource.getVideoUrl()).equals("succeed")) {
					// 创建转码任务 获取任务ID存入数据库
					String jobid = null;
					if(videoResource.getIsWatermark()==IS_WATERMARK){ //1代表需要加水印的视频
						jobid = BaiduMediaTranscodeCommon.createjobByWatermark(videoResource.getVideoUrl());
					}else{
						jobid = BaiduMediaTranscodeCommon.createjob(videoResource.getVideoUrl());
					}
					
					videoResource.setJobid(jobid);
					videoWebService.updateVideo(videoResource);

				} else {
					// 在百度云文件不存在
					videoResource.setState(EnumConstant.VideoStatus.FAILED.getValue().byteValue());
					videoWebService.updateVideo(videoResource);
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}
}*/