from fastapi import FastAPI, Request
from pydantic import BaseModel
from transformers import pipeline
import uvicorn
import os 
os.environ["TF_CPP_MIN_LOG_LEVEL"] = "2"

app = FastAPI()
classifier = pipeline("sentiment-analysis")

class InputText(BaseModel):
    text: str

@app.post("/predict")
async def predict(input_data: InputText):
    result = classifier(input_data.text)
    return {"result": result}

@app.get("/health")
async def health():
    return {"result": "OK"}

if __name__ == "__main__":
    uvicorn.run("app:app", host="0.0.0.0", port=5000)