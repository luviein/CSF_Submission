export interface postComments{
  title: string,
  photo: string,
  description: string,
  tags: string[]
}

export interface TagAndCount {
  tag: string,
  count: number
}

export interface NewsInput{
  id: string,
  postDate: number,
  title: string,
  description: string,
  image: string,
  tags: string[]
}
