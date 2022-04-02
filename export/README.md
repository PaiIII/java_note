## 对应前端
let url = config.apiPrefix + ServiceUrl.bimLightweightMaterialExportExcel;
                //let url = "http://192.168.1.94:8088/bimix/api/bim/lightweight/material/exportExcel";
                axios({method: "get", url: url + "?appId="+ this.props.appId, responseType: "blob",}).then(res => {
                    if (res.data) {
                        // 创建下载的链接
                        const blobUrl = window.URL.createObjectURL(new Blob([res.data]));
                        this.download(blobUrl, decodeURI(res.headers['filename']));
                    }
                });
                break;


download = (blobUrl,fileName)=>{
        const a = document.createElement('a');
        a.style.display = 'none';
        a.download = fileName;
        a.href = blobUrl;
        a.click();
    };