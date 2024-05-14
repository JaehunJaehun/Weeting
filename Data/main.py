from fastapi import FastAPI
# from database import engine, Base
from contextlib import asynccontextmanager
from KoreanProcessing.model import router as fasttext_router
from KoreanProcessing.morpheme import router as konlpy_router
from KoreanProcessing.morpheme import process_message
import asyncio, model_manager, websockets

# async def receive_message():
#     # 배포 서버 URI
#     uri = "ws://54.180.158.223:9002/ws"
#     # 로컬 서버 URI
#     # uri = "ws://localhost:8000/ws/word"
#     async with websockets.connect(uri) as websocket:
#         while True:
#             try:
#                 message = await websocket.recv()
#                 if message:
#                     await process_message(message)
#             except websockets.exceptions.ConnectionClosedError:
#                 print("Connection closed.")
#                 break

@asynccontextmanager
async def lifespan(app: FastAPI):
    try:
        model_manager.load_model()
        # asyncio.create_task(receive_message())
        yield
    except Exception as e:
        print(f"Error during model loading: {e}")
        raise
    finally:
        pass

app = FastAPI(lifespan=lifespan)
app.include_router(fasttext_router)
app.include_router(konlpy_router)

# Base.metadata.create_all(bind=engine)