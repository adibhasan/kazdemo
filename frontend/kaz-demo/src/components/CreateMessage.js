import React, { useState } from 'react';
import Axios from 'axios';

function CreateMessage() {
  const url ="http://localhost:9091/producer/message"
  const [success,setSuccessMessage] = useState('')
  const [error,setErrorMessage] = useState('')
  const [data,setData] = useState({
    messageDataUrl: "",
    messageTitle: "",
    messageId: ""
  })
  function handle(e){
    const newData = {...data}
    newData[e.target.id] = e.target.value
    setData(newData)
    setSuccessMessage('')
    setErrorMessage('')
  }
  function submit(e){
    e.preventDefault()
    Axios.post(url,{
        messageDataUrl: data.messageDataUrl,
        messageTitle: data.messageTitle,
        messageId: data.messageId
    })
    .then(res =>{
        setSuccessMessage("Message queued successfully.")
    })
    .catch(error=>{
        setErrorMessage("Message queued failed.")
    })
  }
  return (
    <div className='container' id="form-wrapper">
        <div className='row'>
            <div className='col'>
                {error && (
                    <p className="alert alert-danger"> {error} </p>
                )}
                {success && (
                    <p className="alert alert-success"> {success} </p>
                )}
            </div>
        </div>
        <div className='row'>
            <div className='col'>
                <form onSubmit={(e)=>submit(e)}>
                    <div className="form-group text-left">
                        <label htmlFor="messageDataUrl">Rest API URL</label>
                        <input onChange={(e) => handle(e)} value={data.messageDataUrl} type="url" className="form-control" id="messageDataUrl" required maxLength="3000"  placeholder="Enter REST API URL" />
                        <small id="message-url-help" className="form-text text-muted help"></small>
                    </div>
                    <div className="form-group">
                        <label htmlFor="messageTitle">Message Title</label>
                        <input onChange={(e) => handle(e)} value={data.messageTitle} type="text" className="form-control"  id="messageTitle"  required maxLength="100"  placeholder="Enter message title" />
                        <small id="message-title-help" className="form-text text-muted help"></small>
                    </div>
                    <div className="form-group">
                        <label htmlFor="messageId">Message ID</label>
                        <input onChange={(e) => handle(e)} value={data.messageId} type="text" className="form-control" id="messageId" required maxLength="11" placeholder="Enter message id eg. AAA-BBB-001" />
                        <small id="message-id-help" className="form-text text-muted help"></small>
                    </div>
                    <div className="form-group">
                        <button type="submit" className="btn btn-primary">Submit</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
  )
}

export default CreateMessage;
